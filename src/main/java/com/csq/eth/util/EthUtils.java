package com.csq.eth.util;

import com.csq.eth.bean.*;
import com.csq.eth.runnable.NewCoinRunnable;
import com.csq.eth.runnable.TransactionRunnable;
import io.reactivex.functions.Consumer;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.*;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class EthUtils {
    private static final Address BNBADDRESS = new Address("0xbb4CdB9CBd36B01bD1cBaEBF2De08d9173bc095c");
    private static final Address ROUTEADDRESS = new Address("0x10ED43C718714eb63d5aA57B78B54704E256024E");
    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://bsc-dataseed.binance.org/"));
//        System.out.println(EthUtils.getTokenBuyPrice(web3j, "0xfa1e1754bd2896d467930c97d07af799c531cb7d", "0xfa1e1754bd2896d467930c97d07af799c531cb7d"));
        Account account = new Account("0xc921ee8793C7B5016f1fF491F82a789D7afF75A7",
                "dcf31046a1272f6efd222ee16992f3c0da6d528a2965279ed974008fa5923821",
                BigDecimal.valueOf(0.0581));
//        String tokenAddress = "0x34AEd5a57Cf8b13f2D6E57B26FCF6f19115ba3Db";
//        System.out.println(EthUtils.getTokenBuyPrice(web3j, "0x815439E8373d14F5275Cdc7856d9Ee83f14Cc838", "0x0f9c381c633555cefa40330e16b10abd107c121d"));

//        web3j.transactionFlowable().filter(transaction -> {
//            return transaction.getFrom().equals("0x414b391b8CE034eFE79C7fc6Acf6469bD20B3F9e".toLowerCase());
//        }).subscribe(transaction -> {
//            System.out.println("t");
//            System.out.println(LocalDateTime.now());
//            System.out.println(transaction.getHash());
//        });

        web3j.pendingTransactionFlowable().filter(transaction -> {
            return transaction.getFrom().equals("0x414b391b8CE034eFE79C7fc6Acf6469bD20B3F9e".toLowerCase());
        }).subscribe(transaction -> {
            System.out.println("pending");
            System.out.println(LocalDateTime.now());
            System.out.println(transaction.getHash());
        });
//        web3j.transactionFlowable().doOnError(throwable -> {
//            System.out.println(throwable);
//        }).
//                filter(transaction->{
//           return "0x10ed43c718714eb63d5aa57b78b54704e256024e".equals(transaction.getTo())
////                   && transaction.getInput().startsWith("0xf305d719")
//                   && transaction.getInput().contains("d8785547f8210b91dfeb740cf6c9c1da158987fd");
//        }).
//                subscribe(transaction -> {
//                    System.out.println("transaction:");
//                    System.out.println(LocalDateTime.now());
//                    System.out.println(transaction.getHash());
//        });


//        System.out.println(web3j.ethGetBlockTransactionCountByNumber(DefaultBlockParameter.valueOf(web3j.ethBlockNumber().send().getBlockNumber())).send().getTransactionCount());
//        EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.PENDING, true).send().getBlock();

//        System.out.println(web3j.ethGetLogs(new EthFilter(DefaultBlockParameter.valueOf(new BigInteger("9583016")),DefaultBlockParameterName.LATEST, "0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c")).send().getLogs());

//        web3j.transactionFlowable()
//        .filter(transaction -> {
//            if ("0x10ed43c718714eb63d5aa57b78b54704e256024e".equals(transaction.getTo())){
//                return true;
//            }
//            return false;
//        }).subscribe(transaction -> {
//            String input = transaction.getInput();
//            System.out.println(transaction.getHash()+" "+input);
//        });
//        "0x7ff36ab50000000000000000000000000000000000000000000000a2deabeb1fe36cc5d100000000000000000000000000000000000000000000000000000000000000800000000000000000000000009a3b136c35909946451f18ea355a411bf69cc4da0000000000000000000000000000000000000000000000000000000061038aa30000000000000000000000000000000000000000000000000000000000000002000000000000000000000000bb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c0000000000000000000000000290cc534c95df34c5484a4e542a7b8b72d94986"
//        BigInteger number = web3j.ethBlockNumber().send().getBlockNumber();

//        String str = "0x7ff36ab5000000000000000000000000000000000000000000a312364c03af76e92ac97b0000000000000000000000000000000000000000000000000000000000000080000000000000000000000000d8e8d432161fabc0b154df329db4493a212591bb00000000000000000000000000000000000000000000000000000000610382880000000000000000000000000000000000000000000000000000000000000002000000000000000000000000bb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c000000000000000000000000ffe6468a95c511783deaadbec0beefd5443453eb";
//        parseInput(str);
//        System.out.println(EthUtils.trading(web3j, approve));


//        TradeInfo sellHexValue = EthUtils.getSellHexValue(web3j, new BigDecimal("103238858"), 18, "0xfa70c2ed7f5f01dabb608ed7809bf5c8b35c48cb", account, web3j.ethGasPrice().send().getGasPrice());
//        System.out.println(EthUtils.trading(web3j, sellHexValue));
//        new Thread(new TransactionRunnable(web3j)).start();
//        double amount = 0.03;
//        double gasPriceK = 2;
//        Account account = new Account("0xFF0b2CD416a5366961927e5fA7f250b5E4d71A5F",
//                "d719165e171439c8099fe84ed22c5fed3cbea7cf443c7f542ccb590079feaf22",
//                BigDecimal.valueOf(0.0581));
//        NewCoinPlan newCoinPlan = new NewCoinPlan(account.getAddress(),tokenAddress,"2021-07-28 16:00:00",amount,gasPriceK);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            new Thread(new NewCoinRunnable(web3j,account,tokenAddress,amount,gasPriceK,simpleDateFormat.parse("2021-07-28 16:00:00").getTime(),newCoinPlan)).start();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long l = System.currentTimeMillis();
//        System.out.println(getTokenBuyPrice(web3j, "0x545c6721314a1064821a8f235a7cef987b4fbc32", "0x0ed3ab9bd31cd062b2c0db2c8f2608fe544c385f"));
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l);
//        long l2 = System.currentTimeMillis();
//        System.out.println(getTokenBuyPrice(web3j, "0x545c6721314a1064821a8f235a7cef987b4fbc32", "0x0ed3ab9bd31cd062b2c0db2c8f2608fe544c385f"));
//        long l3 = System.currentTimeMillis();
//        System.out.println(l3-l2);
//        TradeInfo sellHexValue = EthUtils.getSellHexValue(web3j, new BigDecimal("781046"), 18, "0x194850932e48753cbeedf0af85022152148addc6", account, new BigInteger("5000000000"));
//        System.out.println(EthUtils.trading(web3j, sellHexValue));
//        System.out.println(ifAllowance(web3j,account,"0x3babb320d40b54ee5c0ba29d7a31460884b5996d"));
//        TradeInfo approve = approve(web3j, account, "0x3babb320d40b54ee5c0ba29d7a31460884b5996d", web3j.ethGasPrice().send().getGasPrice());
//        System.out.println(trading(web3j, approve));
//        System.out.println(ifAllowance(web3j,account,"0x3babb320d40b54ee5c0ba29d7a31460884b5996d"));
//        System.out.println(new BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",16));
    }

    public static boolean waitTransactionSuccess(Account account,String transactionHash){
        if (transactionHash == null) {
            return false;
        }
        while (account.getTransactionRecordHashMap().get(transactionHash) == null) {
        }
        TransactionRecord transactionRecord = account.getTransactionRecordHashMap().get(transactionHash);
        if (!transactionRecord.isIfSuccess()) {
            return false;
        }
        return true;
    }
    public static TradeInfo approve(Web3j web3j,Account account,String tokenAddress,BigInteger gasPrice,Plan plan){
        List input = new ArrayList();
        List output = new ArrayList();
        input.add(ROUTEADDRESS);
        input.add(new Uint256(new BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",16)));
        //5分钟
        Function function = new Function("approve",input,output);
        String data = FunctionEncoder.encode(function);
        RawTransaction transaction = RawTransaction.createTransaction(getNonce(web3j, account.getAddress()), gasPrice, BigInteger.valueOf(1000000), tokenAddress, data);
        byte[] signMessage = TransactionEncoder.signMessage(transaction, Credentials.create(account.getPrivateKey()));
        String hexValue = Numeric.toHexString(signMessage);
        return new TradeInfo(
                TransactionMessage.noRecord(account,plan),
                hexValue
        );
    }
    public static Boolean ifAllowance(Web3j web3j,Account account,String tokenAddress) throws IOException {
       try {
           List input = new ArrayList();
           List output = new ArrayList();
           input.add(new Address(account.getAddress()));
           input.add(ROUTEADDRESS);
           output.add(new TypeReference<Uint256>() {});
           //5分钟
           Function function = new Function("allowance",input,output);
           String data = FunctionEncoder.encode(function);
           Transaction ethCallTransaction = Transaction.createEthCallTransaction(account.getAddress(), tokenAddress, data);
           EthCall ethCall = web3j.ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send();
           if (ethCall.hasError()) {
               return null;
           }
           Uint256 result = (Uint256) FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters()).get(0);
           if (result.getValue().compareTo(BigInteger.ZERO) > 0){
               return true;
           }
           return false;
       }catch (Exception e){
           System.out.println(e);
       }
       return null;
    }
    public static String trading(Web3j web3j,TradeInfo tradeInfo) throws IOException {
        EthSendTransaction send = web3j.ethSendRawTransaction(tradeInfo.getHexValue()).send();

        if (!send.hasError()){
            tradeInfo.getTransactionMessage().setTransactionHash(send.getTransactionHash());
            TransactionRunnable.transactionArray.add(tradeInfo.getTransactionMessage());
        }else {
            System.out.println(send.getError().getMessage());
            throw new RuntimeException("事务执行失败");
        }
        return send.getTransactionHash();
    }

    public static TradeInfo getSellHexValue(Web3j web3j, BigDecimal amount, int decimals, String tokenIn, Account account, BigInteger gasPrice , Plan plan) throws IOException {
        List input = new ArrayList();
        List output = new ArrayList();
        input.add(new Uint256(amount.multiply(BigDecimal.TEN.pow(decimals)).toBigInteger()));
        input.add(new Uint256(BigInteger.ZERO));
        input.add(new DynamicArray(Address.class,new Address(tokenIn),BNBADDRESS));
        input.add(new Address(account.getAddress()));
        //5分钟
        input.add(new Uint256(BigInteger.valueOf((System.currentTimeMillis()+1000 * 60 * 60 * 24 *365))));
        output.add(new TypeReference<Uint256>(){});
        Function function = new Function("swapExactTokensForETHSupportingFeeOnTransferTokens",input,output);
        String data = FunctionEncoder.encode(function);
        RawTransaction transaction = RawTransaction.createTransaction(getNonce(web3j, account.getAddress()), gasPrice, BigInteger.valueOf(929358), ROUTEADDRESS.toString(), data);
        byte[] signMessage = TransactionEncoder.signMessage(transaction, Credentials.create(account.getPrivateKey()));
        String hexValue = Numeric.toHexString(signMessage);
        return new TradeInfo(
                new TransactionMessage(account, tokenIn, (byte) 1, amount, null, new BigDecimal(gasPrice).divide(BigDecimal.TEN.pow(18), 18, RoundingMode.HALF_UP),plan),
                hexValue
                );
    }

    public static TradeInfo  getBuyHexValue(Web3j web3j,double amount,String tokenOut,Account account,BigInteger gasPrice,int decimals,Plan plan) throws IOException {
        List input = new ArrayList();
        List output = new ArrayList();
        input.add(new Uint256(BigInteger.ZERO));
        input.add(new DynamicArray(Address.class,BNBADDRESS,new Address(tokenOut)));
        input.add(new Address(account.getAddress()));
        //5分钟
        input.add(new Uint256(BigInteger.valueOf((System.currentTimeMillis()+1000 * 60 * 60 * 24 *365))));
        output.add(new TypeReference<Uint256>(){});
        Function function = new Function("swapExactETHForTokens",input,output);
        String data = FunctionEncoder.encode(function);
        RawTransaction transaction = RawTransaction.createTransaction(getNonce(web3j, account.getAddress()), gasPrice, BigInteger.valueOf(929358), ROUTEADDRESS.toString(), BigInteger.valueOf(Math.round(amount*Math.pow(10,18))), data);
        byte[] signMessage = TransactionEncoder.signMessage(transaction, Credentials.create(account.getPrivateKey()));
        String hexValue = Numeric.toHexString(signMessage);
        return new TradeInfo(
                new TransactionMessage(account, tokenOut, (byte) 0, BigDecimal.valueOf(amount), null, new BigDecimal(gasPrice).divide(BigDecimal.TEN.pow(decimals), decimals, RoundingMode.HALF_UP),plan),
                hexValue
        );
    }
    public static TradeInfo  getBuyHexValueAddSlipPoint(Web3j web3j,double amount,double slipPoint,String tokenOut,Account account,BigInteger gasPrice,int decimals,Plan plan) throws IOException {
        List input = new ArrayList();
        List output = new ArrayList();
        BigDecimal tokenBuyPrice = getTokenBuyPrice(web3j, tokenOut, account.getAddress());
        input.add(new Uint256(new BigDecimal(amount).divide(tokenBuyPrice,decimals,RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(slipPoint)).toBigInteger()));
        input.add(new DynamicArray(Address.class,BNBADDRESS,new Address(tokenOut)));
        input.add(new Address(account.getAddress()));
        //5分钟
        input.add(new Uint256(BigInteger.valueOf((System.currentTimeMillis()+1000 * 60 * 60 * 24 *365))));
        output.add(new TypeReference<Uint256>(){});
        Function function = new Function("swapExactETHForTokens",input,output);
        String data = FunctionEncoder.encode(function);
        RawTransaction transaction = RawTransaction.createTransaction(getNonce(web3j, account.getAddress()), gasPrice, BigInteger.valueOf(929358), ROUTEADDRESS.toString(), BigInteger.valueOf(Math.round(amount*Math.pow(10,18))), data);
        byte[] signMessage = TransactionEncoder.signMessage(transaction, Credentials.create(account.getPrivateKey()));
        String hexValue = Numeric.toHexString(signMessage);
        return new TradeInfo(
                new TransactionMessage(account, tokenOut, (byte) 0, BigDecimal.valueOf(amount), null, new BigDecimal(gasPrice).divide(BigDecimal.TEN.pow(decimals), decimals, RoundingMode.HALF_UP),plan),
                hexValue
        );
    }
//    public static void swapExactETHForTokens(Web3j web3j, double amount, String tokenOut, Account account, BigInteger gasPrice) throws IOException {
//        List input = new ArrayList();
//        List output = new ArrayList();
//        input.add(new Uint256(BigInteger.ZERO));
//        input.add(new DynamicArray(Address.class, BNBADDRESS, new Address(tokenOut)));
//        input.add(new Address(account.getAddress()));
//        //5分钟
//        input.add(new Uint256(BigInteger.valueOf((System.currentTimeMillis() + 1000 * 60 * 5) / 1000)));
//        output.add(new TypeReference<Uint256>() {
//        });
//        Function function = new Function("swapExactETHForTokens", input, output);
//        String data = FunctionEncoder.encode(function);
//        RawTransaction transaction = RawTransaction.createTransaction(getNonce(web3j, account.getAddress()), gasPrice, BigInteger.valueOf(929358), ROUTEADDRESS.toString(), BigInteger.valueOf(Math.round(amount * Math.pow(10, 18))), data);
//        byte[] signMessage = TransactionEncoder.signMessage(transaction, Credentials.create(account.getAddress()));
//        String hexValue = Numeric.toHexString(signMessage);
//        EthSendTransaction send = web3j.ethSendRawTransaction(hexValue).send();
//        System.out.println(send.getTransactionHash());
//        System.out.println(send.getError().getMessage());
//        if (!send.hasError()) {
//            TransactionRunnable.transactionArray.add(new TransactionMessage(account, tokenOut, (byte) 0, new BigDecimal(amount), send.getTransactionHash(), new BigDecimal(gasPrice).divide(BigDecimal.TEN.pow(18), 18, RoundingMode.HALF_UP)));
//        }
//    }

    public static BigDecimal getTokenBuyPrice(Web3j web3j, String tokenAddress, String myAddress) throws IOException {
        try {
            BigInteger decimals = getDecimals(web3j, tokenAddress, myAddress);
            List input = new ArrayList();
            List output = new ArrayList();
            input.add(new Uint256(BigInteger.TEN.pow(decimals.intValue())));
            input.add(new DynamicArray(Address.class, BNBADDRESS, new Address(tokenAddress)));
            output.add(new TypeReference<DynamicArray<Uint256>>() {
            });
            Function function = new Function("getAmountsIn", input, output);
            String data = FunctionEncoder.encode(function);
            Transaction transaction = Transaction.createEthCallTransaction(myAddress, ROUTEADDRESS.toString(), data);
            EthCall send = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            if (send.hasError()) {
                return null;
            }
            DynamicArray<Uint256> result = (DynamicArray<Uint256>) FunctionReturnDecoder.decode(send.getValue(), output).get(0);
            return new BigDecimal(result.getValue().get(0).getValue()).divide(new BigDecimal(Math.pow(10, 18)), 18, RoundingMode.HALF_UP);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static BigDecimal getTokenSellPrice(Web3j web3j, String tokenAddress, String myAddress) throws IOException {
        BigInteger decimals = getDecimals(web3j, tokenAddress, myAddress);
        List input = new ArrayList<>();
        List output = new ArrayList<>();
        input.add(new Uint256(BigInteger.TEN.pow(decimals.intValue())));
        input.add(new DynamicArray(Address.class, new Address(tokenAddress), BNBADDRESS));
        output.add(new TypeReference<DynamicArray<Uint256>>() {
        });
        Function function = new Function("getAmountsOut", input, output);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(myAddress, ROUTEADDRESS.toString(), data);
        EthCall send = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        if (send.hasError()) {
            return null;
        }
        DynamicArray<Uint256> result = (DynamicArray<Uint256>) FunctionReturnDecoder.decode(send.getValue(), output).get(0);
        return new BigDecimal(result.getValue().get(1).getValue()).divide(new BigDecimal(Math.pow(10, 18)), 18, RoundingMode.HALF_UP);
    }

    public static BigInteger getDecimals(Web3j web3j, String tokenAddress, String myAddress) throws IOException {
        List<Type> input = new ArrayList<>();
        List<TypeReference<?>> output = new ArrayList<>();
        output.add(new TypeReference<Uint256>() {
        });
        Function function = new Function("decimals",
                input,
                output);
        String data = FunctionEncoder.encode(function);

        Transaction transaction = Transaction.createEthCallTransaction(myAddress, tokenAddress, data);
        EthCall result = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        return (BigInteger) FunctionReturnDecoder.decodeIndexedValue(result.getValue(), new TypeReference<Uint256>() {
        }).getValue();
    }

    public static BigInteger getNonce(Web3j web3j, String addr) {
        try {
            EthGetTransactionCount getNonce = web3j.ethGetTransactionCount(addr, DefaultBlockParameterName.PENDING).send();
            if (getNonce == null) {
                throw new RuntimeException("net error");
            }
            return getNonce.getTransactionCount();
        } catch (IOException e) {
            throw new RuntimeException("net error");
        }
    }

    public static BigDecimal getTokenBalance(Web3j web3j, String tokenAddress, String myAddress) throws IOException {

        String methodName = "balanceOf";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();
        Address address = new Address(myAddress);
        inputParameters.add(address);

        TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
        };
        outputParameters.add(typeReference);
        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(myAddress, tokenAddress, data);

        EthCall ethCall;
        BigInteger balanceValue = BigInteger.ZERO;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            balanceValue = (BigInteger) results.get(0).getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int decimals = getDecimals(web3j, tokenAddress, myAddress).intValue();
        return new BigDecimal(balanceValue).divide(new BigDecimal(BigInteger.TEN.pow(decimals)), decimals, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBalance(Web3j web3j, String fromAddress) {
        String methodName = "balanceOf";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();
        Address address = new Address(fromAddress);
        inputParameters.add(address);

        TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
        };
        outputParameters.add(typeReference);
        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, BNBADDRESS.getValue(), data);

        EthCall ethCall;
        BigDecimal balanceValue = null;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            balanceValue = Convert.fromWei(new BigDecimal((BigInteger) results.get(0).getValue()), Convert.Unit.ETHER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balanceValue;
    }

    public static String getTokenName(Web3j web3j, String tokenAddress, String myAddress) throws IOException {
        List<Type> input = new ArrayList<>();
        List<TypeReference<?>> output = new ArrayList<>();
        output.add(new TypeReference<Utf8String>() {
        });
        Function function = new Function("name",
                input,
                output);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(myAddress, tokenAddress, data);
        EthCall result = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
        return (String) FunctionReturnDecoder.decode(result.getValue(), function.getOutputParameters()).get(0).getValue();
    }

}
