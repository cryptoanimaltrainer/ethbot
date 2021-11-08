package com.csq.eth.bean;
//        address: '',
//        tokenAddress: '',
//        date: '',
//        time: '',
//        amount: '',
//        gasPriceK: '',
//        sellPriceK: '',
//        sellNumK: '',
//        blockNumber: '',
//        slipPoint: '',
//        ifRetry: false
public class PlanDto {
    private Long id;
    private String tokenName;
    private Integer status;
    private String address;
    private String tokenAddress;
    private String dateTime;
    private String date;
    private String time;
    private double amount;
    private double gasPriceK;
    private double sellPriceK;
    private double sellNumK;
    private Integer blockNumber;
    private Double slipPoint;
    private Boolean ifRetry;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getGasPriceK() {
        return gasPriceK;
    }

    public void setGasPriceK(double gasPriceK) {
        this.gasPriceK = gasPriceK;
    }

    public double getSellPriceK() {
        return sellPriceK;
    }

    public void setSellPriceK(double sellPriceK) {
        this.sellPriceK = sellPriceK;
    }

    public double getSellNumK() {
        return sellNumK;
    }

    public void setSellNumK(double sellNumK) {
        this.sellNumK = sellNumK;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Double getSlipPoint() {
        return slipPoint;
    }

    public void setSlipPoint(Double slipPoint) {
        this.slipPoint = slipPoint;
    }

    public Boolean getIfRetry() {
        return ifRetry;
    }


    public void setIfRetry(Boolean ifRetry) {
        this.ifRetry = ifRetry;
    }

    @Override
    public String toString() {
        return "PlanDto{" +
                "address='" + address + '\'' +
                ", tokenAddress='" + tokenAddress + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", amount=" + amount +
                ", gasPriceK=" + gasPriceK +
                ", sellPriceK=" + sellPriceK +
                ", sellNumK=" + sellNumK +
                ", blockNumber=" + blockNumber +
                ", slipPoint=" + slipPoint +
                ", ifRetry=" + ifRetry +
                '}';
    }
}
