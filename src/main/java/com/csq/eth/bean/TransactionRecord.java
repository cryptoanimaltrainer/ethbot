package com.csq.eth.bean;

public class TransactionRecord {
    private String transactionHash;
    private boolean ifSuccess;

    public TransactionRecord(String transactionHash, boolean ifSuccess) {
        this.transactionHash = transactionHash;
        this.ifSuccess = ifSuccess;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public boolean isIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }
}
