package com.csq.eth.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Account {
    private String address;
    private String privateKey;
    private BigDecimal bnbAmount = new BigDecimal("0");
    private Map<String,Coin> coinMap = new ConcurrentHashMap<>();
    private Map<String,TransactionRecord> transactionRecordHashMap = new ConcurrentHashMap<>();
    private List<Transaction> transactionList = new CopyOnWriteArrayList<>();

    public Account(String address, String privateKey, BigDecimal bnbAmount) {
        this.address = address;
        this.privateKey = privateKey;
        this.bnbAmount = bnbAmount;
    }
    public Account(String address, String privateKey) {
        this.address = address;
        this.privateKey = privateKey;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public BigDecimal getBnbAmount() {
        return bnbAmount;
    }

    public void setBnbAmount(BigDecimal bnbAmount) {
        this.bnbAmount = bnbAmount;
    }

    public Map<String, Coin> getCoinMap() {
        return coinMap;
    }

    public Map<String, TransactionRecord> getTransactionRecordHashMap() {
        return transactionRecordHashMap;
    }

    public void setTransactionRecordHashMap(Map<String, TransactionRecord> transactionRecordHashMap) {
        this.transactionRecordHashMap = transactionRecordHashMap;
    }

    public void setCoinMap(Map<String, Coin> coinMap) {
        this.coinMap = coinMap;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("账户地址:"+address+"\n");
        stringBuilder.append("账户私钥:"+privateKey+"\n");
        stringBuilder.append("账户bnb数量:"+bnbAmount+"\n");
        stringBuilder.append("账户持币明细:\n");
        coinMap.values().forEach(coin -> {
            stringBuilder.append("\t"+coin+"\n");
        });
        stringBuilder.append("账户事务记录:\n");
        transactionList.forEach(transaction -> {
            stringBuilder.append("\t"+transaction+"\n");

        });
        return stringBuilder.toString();
    }
}
