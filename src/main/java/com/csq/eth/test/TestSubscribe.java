package com.csq.eth.test;

import com.csq.eth.util.EthUtils;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import org.web3j.abi.Utils;
import org.web3j.utils.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.methods.response.TxPoolContent;
import org.web3j.protocol.core.methods.request.Filter;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TestSubscribe {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Web3j web3j = Web3j.build(new HttpService("http://8.134.77.157/rpc"));
        Geth geth = Geth.build(new HttpService("http://8.134.77.157/rpc"));
        Map<String,Boolean> blackMap = new HashMap();
//        result.getQueuedTransactions().forEach(transaction -> {
//            System.out.println(transaction.getBlockHash()+":"+transaction.getHash() );
//        });
        System.out.println(geth.ethGasPrice().send().getGasPrice());
        for (int i = 0; i < 2; i++) {
            long l = System.currentTimeMillis();
            TxPoolContent txPoolContent = geth.txPoolContent().send();
            int finalFirst = i;
            txPoolContent.getResult().getPendingTransactions().forEach(transaction -> {
                try {
//                if (!geth.ethGetTransactionByHash(transaction.getHash()).send().getTransaction().isPresent()) {
//                    System.out.println(transaction.getHash());
//                }
                    if (transaction.getTo().toLowerCase().equals("0x10ed43c718714eb63d5aa57b78b54704e256024e") && !transaction.getFrom().equals("0xefc6162e5d04f286b66c0a7885333f0929b0d661")) {
//                        if (finalFirst == 0){
//                            if (transaction.getGasPrice().compareTo(BigInteger.valueOf(5000000000L))<0 ||(EthUtils.getNonce(geth, transaction.getFrom()).subtract(BigInteger.ONE).compareTo(transaction.getNonce()) != 0)){
//                                return;
//                            }
//                            blackMap.put(transaction.getHash(),true);
//                            return;
//                        }
//                        if (blackMap.get(transaction.getHash()) != null){
//                            System.out.println("存在"+transaction.getHash());
//                            return;
//                        }
                    if (blackMap.get(transaction.getFrom()) != null){
                        return;
                    }
                    if (transaction.getGasPrice().compareTo(BigInteger.valueOf(5000000000L))<0 ||(EthUtils.getNonce(geth, transaction.getFrom()).subtract(BigInteger.ONE).compareTo(transaction.getNonce()) != 0)){
                        blackMap.put(transaction.getFrom(),true);
                        return;
                    }
//                    System.out.println(EthUtils.getNonce(geth, transaction.getFrom()));
                        System.out.println(LocalDateTime.now() +transaction.getFrom()+":"+transaction.getNonce()+ ":" + transaction.getHash() +":"+transaction.getGasPrice()+ ":" + transaction.getGas());
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            });
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);
            Thread.sleep(4000);
        }


//        web3j.pendingTransactionFlowable()
//        web3j.pendingTransactionFlowable().doOnError(throwable -> {
//            System.out.println(throwable);
//        }).
//                filter(transaction -> {
//            return transaction.getTo().equals("0x10ed43c718714eb63d5aa57b78b54704e256024e");
//        }).
//                subscribe(transaction -> {
//
//            System.out.println(transaction.getHash()+ "    to:"+transaction.getTo());
//        });

    }
}
