package com.csq.eth.bean;

import java.math.BigDecimal;

public class TransactionMessage {
    private Account account;
    private String tokenAddress;
    //0代表买入,1代表卖出,-1代表不记录
    private byte status;
    private BigDecimal amountIn;
    private String transactionHash;
    private BigDecimal gasPrice;
    private Plan plan;


    public TransactionMessage(Account account, String tokenAddress, byte status, BigDecimal amountIn, String transactionHash, BigDecimal gasPrice , Plan plan) {
        this.account = account;
        this.tokenAddress = tokenAddress;
        this.status = status;
        this.amountIn = amountIn;
        this.transactionHash = transactionHash;
        this.gasPrice = gasPrice;
        this.plan = plan;
    }

    public static TransactionMessage noRecord(Account account,Plan plan){
        return new TransactionMessage(account,null, (byte) -1,null,null,null,plan);
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigDecimal getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }
}
