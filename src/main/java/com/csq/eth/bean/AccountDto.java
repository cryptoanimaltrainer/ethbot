package com.csq.eth.bean;

public class AccountDto {
    private String address;
    private String privateKey;

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

    @Override
    public String toString() {
        return "AccountDto{" +
                "address='" + address + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
