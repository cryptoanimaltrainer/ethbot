package com.csq.eth.bean;

import com.csq.eth.util.EthUtils;
import org.web3j.protocol.Web3j;
import java.io.IOException;
import java.math.BigDecimal;
public class Coin {
    private String address;
    private String name;
    private int decimals;
    //持有数量
    private BigDecimal number = BigDecimal.ZERO;
    //买入的平均价格
    private BigDecimal avePrice = BigDecimal.ZERO;

    public Coin(Web3j web3j, String myAddress, String address) throws IOException {
        this.address = address;
        name = EthUtils.getTokenName(web3j,address,myAddress);
        decimals = EthUtils.getDecimals(web3j,address,myAddress).intValue();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(BigDecimal avePrice) {
        this.avePrice = avePrice;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", decimals=" + decimals +
                ", number=" + number +
                ", avePrice=" + avePrice +
                '}';
    }
}
