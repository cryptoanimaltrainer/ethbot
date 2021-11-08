//package com.csq.eth.bot;
//import com.csq.eth.bean.Account;
//import com.csq.eth.bean.NewCoinPlan;
//import com.csq.eth.bean.Plan;
//import com.csq.eth.runnable.NewCoinRunnable;
//import com.csq.eth.runnable.TransactionRunnable;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.http.HttpService;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Bot {
//    public static void main(String[] args) throws IOException {
//        Web3j web3j = Web3j.build(new HttpService("https://bsc-dataseed.binance.org/"));
//        new Thread(new TransactionRunnable(web3j)).start();
//        Scanner scanner = new Scanner(System.in);
//        Map<String,Account> accountMap = new HashMap<>();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        while (true){
//            String accountAddress = null;
//            Account account = null;
//            try {
//                System.out.println("请选择功能:");
//                System.out.println("\t1.添加用户");
//                System.out.println("\t2.制定抢新币计划");
//                System.out.println("\t3.撤销计划");
//                System.out.println("\t4.查看用户信息");
//                int i = scanner.nextInt();
//                switch (i){
//                    case 1:
//                        System.out.println("请输入地址:");
//                        String address = scanner.next();
//                        System.out.println("请输入私钥:");
//                        String privateKey = scanner.next();
//                        System.out.println("请输入可用bnb数量:");
//                        String bnbNumber = scanner.next();
//                        account = new Account(address,privateKey,new BigDecimal(bnbNumber));
//                        accountMap.put(address,account);
//                        System.out.println("用户添加成功");
//                        break;
//                    case 2:
//                        System.out.println("请输入用户地址:");
//                        accountAddress = scanner.next();
//                        account = accountMap.get(accountAddress);
//                        System.out.println("请输入项目开始日期(yyyy-MM-dd):");
//                        String dateStr = scanner.next();
//                        System.out.println("请输入项目开始时间(HH:mm:ss):");
//                        String timeStr = scanner.next();
//                        String dateTimeStr = dateStr +" "+timeStr;
//                        Date date = simpleDateFormat.parse(dateTimeStr);
//                        System.out.println("请输入购买数量:");
//                        double amount = scanner.nextDouble();
//                        System.out.println("请输入gas费系数:");
//                        double gasPriceK = scanner.nextDouble();
//                        System.out.println("请输入多少倍卖出:");
//                        double sellPriceK = scanner.nextDouble();
//                        System.out.println("请输入卖出比例:");
//                        double sellNumK = scanner.nextDouble();
//                        System.out.println("请输入token地址:");
//                        String tokenAddress = scanner.next();
//                        NewCoinPlan newCoinPlan = new NewCoinPlan(account.getAddress(),tokenAddress,dateTimeStr,amount,gasPriceK);
////                        account.getPlans().add(newCoinPlan);
//                        new Thread(new NewCoinRunnable(web3j, account, tokenAddress, amount, gasPriceK, date.getTime(),newCoinPlan,sellPriceK,sellNumK)).start();
//                        break;
//                    case 3:
//                        System.out.println("请输入用户地址:");
//                        accountAddress = scanner.next();
//                        account = accountMap.get(accountAddress);
//                        System.out.println("请输入需要撤销的计划编号:");
////                        List<Plan> plans = account.getPlans();
////                        for (int j = 0; j < plans.size(); j++) {
////                            System.out.println(j+"."+plans.get(j)+"\n");
////                        }
////                        int index = scanner.nextInt();
////                        plans.get(index).cancelPlan();
////                        plans.remove(index);
//                        System.out.println("计划撤销完成");
//                        break;
//                    case 4:
//                        System.out.println("请输入用户地址:");
//                        accountAddress = scanner.next();
//                        account = accountMap.get(accountAddress);
//                        System.out.println(account);
//                        break;
//                }
//            }catch (Exception e){
//                System.out.println(e);
//            }
//        }
//
//
//
//    }
//}
