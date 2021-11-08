package com.csq.eth.bean;

public class TradeInfo {
    private TransactionMessage transactionMessage;
    private String HexValue;

    public TradeInfo(TransactionMessage transactionMessage, String hexValue) {
        this.transactionMessage = transactionMessage;
        HexValue = hexValue;
    }

    public TransactionMessage getTransactionMessage() {
        return transactionMessage;
    }

    public void setTransactionMessage(TransactionMessage transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public String getHexValue() {
        return HexValue;
    }

    public void setHexValue(String hexValue) {
        HexValue = hexValue;
    }
}
