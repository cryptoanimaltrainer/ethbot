package com.csq.eth.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {
    private String transactionHash;
    private BigDecimal transactionFee;
    //0代表买入,1代表卖出
    private byte status;
    private String tokenAddress;
    private String tokenName;
    private BigDecimal amountIn;
    private BigDecimal amountOut;

    public Transaction(String transactionHash, BigDecimal transactionFee, byte status, String tokenAddress, String tokenName, BigDecimal amountIn, BigDecimal amountOut) {
        this.transactionHash = transactionHash;
        this.transactionFee = transactionFee;
        this.status = status;
        this.tokenAddress = tokenAddress;
        this.tokenName = tokenName;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
    }

    @Override
    public String toString() {
        if (status == 0){
            return "买入操作:  花费"+amountIn+"BNB买入了"+amountOut+"枚"+tokenName+"("+tokenAddress+"),买入价格:" +(amountIn.divide(amountOut,18, RoundingMode.HALF_UP)).toPlainString();
        }else if (status == 1){
            return "卖出操作:  消耗"+amountIn+"枚"+tokenName+"("+tokenAddress+")"+"兑换了"+amountOut+"BNB,卖出价格: "+(amountOut.divide(amountIn,18,RoundingMode.HALF_UP).toPlainString());
        }
        return null;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public BigDecimal getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }

    public BigDecimal getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }
}
