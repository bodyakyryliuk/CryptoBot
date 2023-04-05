package com.company.DataAnalysis;

import java.util.ArrayList;
import java.util.List;

public class CryptoPrices {
    public List<List<Float>> cryptoPrices = new ArrayList<>();

    public CryptoPrices(){
        List<Float> btcPrices = new ArrayList<>();
        List<Float> ethPrices = new ArrayList<>();
        List<Float> twtPrices = new ArrayList<>();
        List<Float> xrpPrices = new ArrayList<>();

        cryptoPrices.add(btcPrices);
        cryptoPrices.add(ethPrices);
        cryptoPrices.add(twtPrices);
        cryptoPrices.add(xrpPrices);
    }

    public void addCryptoPrices(int n, float price){
        cryptoPrices.get(n).add(price);
    }

    public float getCryptoPrices(int n){
        int size = cryptoPrices.get(n).size();
        if(size > 0)
            return cryptoPrices.get(n).get(size-1);
        else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return getCryptoPrices(n);
        }
    }


}
