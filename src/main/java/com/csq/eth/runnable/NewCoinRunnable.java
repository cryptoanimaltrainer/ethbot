package com.csq.eth.runnable;

import com.csq.eth.bean.*;
import com.csq.eth.controller.Page;
import com.csq.eth.util.EthUtils;
import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewCoinRunnable implements Runnable {
    private Web3j web3j;
    private Plan plan;
    private Account account;
    private String tokenAddress;
    private long beginTime;
    private double amount;
    private BigInteger gasPrice;
    private double sellPriceK;
    private double sellNumK;
    private Integer blockNumber;
    private Double slipPoint;
    private Boolean ifRetry;
    private int decimals;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public NewCoinRunnable(Web3j web3j, Plan plan) throws IOException, ParseException {
        NewCoinPlan newCoinPlan = (NewCoinPlan) plan;
        PlanDto planDto = newCoinPlan.getPlanDto();
        this.web3j = web3j;
        this.account = Page.accountMap.get(planDto.getAddress());
        this.tokenAddress = planDto.getTokenAddress().trim();
        this.amount = planDto.getAmount();
        gasPrice = new BigDecimal(web3j.ethGasPrice().send().getGasPrice()).multiply(new BigDecimal(planDto.getGasPriceK())).toBigInteger();
        this.beginTime = simpleDateFormat.parse(planDto.getDate() + " " + planDto.getTime()).getTime();
        this.plan = newCoinPlan;
        this.sellNumK = planDto.getSellNumK();
        this.sellPriceK = planDto.getSellPriceK();
        this.blockNumber = planDto.getBlockNumber();
        if (slipPoint != null) {
            this.slipPoint = planDto.getSlipPoint()/100;
        }
        this.ifRetry = planDto.getIfRetry();
        this.decimals = EthUtils.getDecimals(web3j, tokenAddress, account.getAddress()).intValue();
    }

    @Override
    public void run() {
        try {
            if (beginTime - System.currentTimeMillis() > 1000 * 60 * 3) {
                plan.addLog("???????????????????????????????????????");
                try {
                    Thread.sleep(beginTime - System.currentTimeMillis() - 1000 * 60 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (plan.ifFinish()) {
                plan.addLog("???????????????");
                throw new RuntimeException("???????????????");
            }
            plan.addLog("*****??????????????????*****(??????????????????????????????????????????????????????????????????)");
            plan.beginPlan();
            TradeInfo buyHexValue = EthUtils.getBuyHexValue(web3j, amount, tokenAddress, account, gasPrice, decimals,plan);
            while (EthUtils.getTokenBuyPrice(web3j, tokenAddress, account.getAddress()) == null) {
                if (plan.ifFinish()) {
                    throw new RuntimeException("???????????????");
                }
            }
            if (blockNumber != null && blockNumber > 0) {
                BigInteger currentNumber = web3j.ethBlockNumber().send().getBlockNumber();
                while (web3j.ethBlockNumber().send().getBlockNumber().subtract(currentNumber).compareTo(new BigInteger(blockNumber + "")) < 0) {
                }
            }
            if (slipPoint != null && slipPoint >= 0 && slipPoint < 1) {
                buyHexValue = EthUtils.getBuyHexValueAddSlipPoint(web3j, amount, slipPoint, tokenAddress, account, gasPrice, decimals,plan);
            }
            String buyTransactionHash = EthUtils.trading(web3j, buyHexValue);
            plan.addLog("?????????????????????(" + buyTransactionHash + ")");
            if (ifRetry) {
                while (!EthUtils.waitTransactionSuccess(account, buyTransactionHash)) {
                    plan.addLog("????????????,????????????");
                    if (slipPoint != null && slipPoint >= 0 && slipPoint < 1) {
                        buyHexValue = EthUtils.getBuyHexValueAddSlipPoint(web3j, amount, slipPoint, tokenAddress, account, gasPrice, decimals,plan);
                    } else {
                        buyHexValue = EthUtils.getBuyHexValue(web3j, amount, tokenAddress, account, gasPrice, decimals,plan);
                    }
                    buyTransactionHash = EthUtils.trading(web3j, buyHexValue);
                    plan.addLog("?????????????????????(" + buyTransactionHash + ")");
                }
            } else if (!EthUtils.waitTransactionSuccess(account, buyTransactionHash)) {
                throw new RuntimeException("????????????????????????");
            }
            //?????????coin??????????????????
            Boolean ifAllowance = EthUtils.ifAllowance(web3j, account, tokenAddress);
            while (ifAllowance == null) {
                ifAllowance = EthUtils.ifAllowance(web3j, account, tokenAddress);
                if (plan.ifFinish()) {
                    throw new RuntimeException("???????????????");
                }
            }
            //??????????????????????????????
            if (ifAllowance == false) {
                TradeInfo approve = EthUtils.approve(web3j, account, tokenAddress, gasPrice,plan);
                String approveTransactionHash = EthUtils.trading(web3j, approve);
                if (!EthUtils.waitTransactionSuccess(account, approveTransactionHash)) {
                    throw new RuntimeException("????????????????????????");
                }
            }
            Coin coin = account.getCoinMap().get(tokenAddress);
            plan.addLog(coin.getName() + "(" + coin.getAddress() + ")???????????????");
            BigDecimal goalPrice = coin.getAvePrice().multiply(BigDecimal.valueOf(sellPriceK));
            TradeInfo sellHexValue = EthUtils.getSellHexValue(web3j, coin.getNumber().multiply(BigDecimal.valueOf(sellNumK)), coin.getDecimals(), tokenAddress, account, gasPrice,plan);
            BigDecimal tokenBuyPrice = EthUtils.getTokenBuyPrice(web3j, tokenAddress, account.getAddress());
            while (tokenBuyPrice == null || tokenBuyPrice.compareTo(goalPrice) < 0) {
                tokenBuyPrice = EthUtils.getTokenBuyPrice(web3j, tokenAddress, account.getAddress());
                if (plan.ifFinish()) {
                    throw new RuntimeException("???????????????");
                }
            }
            EthUtils.trading(web3j, sellHexValue);
            plan.addLog("?????????????????????");
            plan.addLog(account.getAddress() + "????????????" + coin.getName() + "(" + coin.getAddress() + ")???????????????");
        } catch (IOException e) {
            plan.addLog(e.toString());
        } finally {
            plan.completePlan();
        }
    }
}
