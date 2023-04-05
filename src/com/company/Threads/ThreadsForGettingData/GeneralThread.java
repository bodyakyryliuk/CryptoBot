package com.company.Threads.ThreadsForGettingData;

import com.company.DataAccess;
import com.company.DataAnalysis.*;

import java.io.*;


public class GeneralThread implements Runnable{
    private final String name;
    private final CryptoPrices cryptoPrices;

    public GeneralThread(String name, CryptoPrices cryptoPrices){
        this.name = name;
        this.cryptoPrices = cryptoPrices;
    }


    @Override
    public void run() {
        float val ;
        while (true) {
            switch (name) {
                case "BTC" -> {
                    Object checkNull = DataAccess.getBTCDataString().get("BTCUSDT");

                    if(checkNull!=null) {
                        val = (float)checkNull;
                        cryptoPrices.addCryptoPrices(0, val);
                    }
                }
                case "ETH" -> {
                    Object checkNull = DataAccess.getETHDataString().get("ETHUSDT");

                    if(checkNull!=null) {
                        val = (float)checkNull;
                        cryptoPrices.addCryptoPrices(1, val);
                    }
                }
                case "TWT" -> {
                    Object checkNull = DataAccess.getTWTDataString().get("TWTUSDT");

                    if(checkNull!=null) {
                        val = (float)checkNull;
                        cryptoPrices.addCryptoPrices(2, val);
                    }
                }
                case "XRP" -> {
                    Object checkNull = DataAccess.getXRPDataString().get("XRPUSDT");

                    if (checkNull != null) {
                        val = (float)checkNull;
                        cryptoPrices.addCryptoPrices(3, val);
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
