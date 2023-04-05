package com.company;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private List<Float> currencyAmount = new ArrayList<>();
    private List<Float> buyPrice = new ArrayList<>();
    private List<Float> sellPrice = new ArrayList<>();

    private float USDTAmount;

    public Wallet (float usdt, float btc, float eth, float twt, float xrp){
        USDTAmount = usdt;

        currencyAmount.add(btc);
        currencyAmount.add(eth);
        currencyAmount.add(twt);
        currencyAmount.add(xrp);

        buyPrice.add(0f);
        buyPrice.add(0f);
        buyPrice.add(0f);
        buyPrice.add(0f);

        sellPrice.add(0f);
        sellPrice.add(0f);
        sellPrice.add(0f);
        sellPrice.add(0f);
    }

    public void printGeneralBalance(){
        System.out.println("BTC: "+getCurrencyAmount(0));
        System.out.println("ETH: "+getCurrencyAmount(1));
        System.out.println("TWT: "+getCurrencyAmount(2));
        System.out.println("XRP: "+getCurrencyAmount(3));
        System.out.println("USDT: "+USDTAmount);
    }

    public void setSellPrice(int n, float price){
        sellPrice.set(n,price);
    }

    public float getSellPrice(int n){
        return sellPrice.get(n);
    }

    public void setCurrencyAmount(int n, float amount){
        currencyAmount.set(n, amount);
    }

    public void setBuyPrice(int n, float price){
        buyPrice.set(n, price);
    }

    public float getCurrencyAmount(int n){
        return currencyAmount.get(n);
    }

    public float getBuyPrice(int n){
        return buyPrice.get(n);
    }

    public float getBalance() {
        return USDTAmount;
    }

    public void addUSDT(float usdt){
        USDTAmount += usdt;
    }

    public void setBalance(float balance) {
        USDTAmount = balance;
    }

}
