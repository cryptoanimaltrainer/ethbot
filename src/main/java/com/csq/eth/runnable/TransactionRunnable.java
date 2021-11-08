package com.csq.eth.runnable;

import com.csq.eth.bean.*;
import lombok.SneakyThrows;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionRunnable implements Runnable {
    public static List<TransactionMessage> transactionArray = new CopyOnWriteArrayList<>();
    private static final String TRANSFERHASH = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";
    private Web3j web3j;

    public TransactionRunnable(Web3j web3j) {
        this.web3j = web3j;

    }


    @Override
    public void run() {
        while (true) {
            while (transactionArray.size() == 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            TransactionMessage transactionMessage = transactionArray.get(0);
            try {
                EthGetTransactionReceipt send = web3j.ethGetTransactionReceipt(transactionMessage.getTransactionHash()).send();
                while (!send.getTransactionReceipt().isPresent()) {
                    Thread.sleep(1000);
                    send = web3j.ethGetTransactionReceipt(transactionMessage.getTransactionHash()).send();
                }

                TransactionReceipt transactionReceipt = send.getTransactionReceipt().get();
                Account account = transactionMessage.getAccount();
                if ("0x1".equals(transactionReceipt.getStatus())) {
                    if (transactionMessage.getStatus() != -1) {
                        String transactionHash = transactionReceipt.getTransactionHash();
                        Map<String, Coin> coinMap = account.getCoinMap();
                        Coin coin = coinMap.get(transactionMessage.getTokenAddress());
                        if (coin == null) {
                            coin = new Coin(web3j, account.getAddress(), transactionMessage.getTokenAddress());
                            coinMap.put(transactionMessage.getTokenAddress(), coin);
                        }
                        List<Log> logs = transactionReceipt.getLogs();
                        BigDecimal amountOut = null;
                        for (int i = logs.size() - 1; i >= 0; i--) {
                            if (TRANSFERHASH.equals(logs.get(i).getTopics().get(0))) {
                                if (transactionMessage.getStatus() == 0) {
                                    Uint256 uint256 = (Uint256) FunctionReturnDecoder.decodeIndexedValue(logs.get(i).getData(), new TypeReference<Uint256>() {
                                    });
                                    amountOut = new BigDecimal((BigInteger) uint256.getValue()).divide(BigDecimal.TEN.pow(coin.getDecimals()), coin.getDecimals(), RoundingMode.HALF_UP);
                                    coin.setAvePrice(
                                            transactionMessage.getAmountIn().divide(amountOut, 18, RoundingMode.HALF_UP).multiply(amountOut).add(coin.getNumber().multiply(coin.getAvePrice())).divide(amountOut.add(coin.getNumber()), 18, RoundingMode.HALF_UP)
                                    );
                                    coin.setNumber(coin.getNumber().add(amountOut));
                                    account.setBnbAmount(account.getBnbAmount().add(transactionMessage.getAmountIn()));
                                } else if (transactionMessage.getStatus() == 1) {
                                    Uint256 uint256 = (Uint256) FunctionReturnDecoder.decodeIndexedValue(logs.get(i).getData(), new TypeReference<Uint256>() {
                                    });
                                    amountOut = new BigDecimal((BigInteger) uint256.getValue()).divide(BigDecimal.TEN.pow(18), 18, RoundingMode.HALF_UP);
                                    coin.setNumber(coin.getNumber().subtract(transactionMessage.getAmountIn()));
                                    account.setBnbAmount(account.getBnbAmount().subtract(amountOut));
                                }
                                break;
                            }
                        }
                        BigDecimal gasFee = transactionMessage.getGasPrice().multiply(new BigDecimal(transactionReceipt.getGasUsed()));
                        account.setBnbAmount(account.getBnbAmount().subtract(gasFee));
                        Transaction transaction = new Transaction(
                                transactionHash,
                                transactionMessage.getGasPrice().multiply(new BigDecimal(transactionReceipt.getGasUsed())),
                                transactionMessage.getStatus(),
                                transactionMessage.getTokenAddress(),
                                coin.getName(),
                                transactionMessage.getAmountIn(),
                                amountOut
                        );
                        account.getTransactionList().add(transaction);
                        transactionMessage.getPlan().addLog(transaction.toString());
                    }
                    account.getTransactionRecordHashMap().put(transactionMessage.getTransactionHash(), new TransactionRecord(
                            transactionMessage.getTransactionHash(),
                            true
                    ));
                } else if ("0x0".equals(transactionReceipt.getStatus())) {
                    account.getTransactionRecordHashMap().put(transactionMessage.getTransactionHash(), new TransactionRecord(
                            transactionMessage.getTransactionHash(),
                            false
                    ));
                    transactionMessage.getPlan().addLog("事务" + transactionMessage.getTransactionHash() + "执行失败!");
                }
                transactionArray.remove(0);
            } catch (Throwable e) {
                e.printStackTrace();
                transactionMessage.getPlan().addLog(e.toString());
            }
        }
    }


}
