package com.company.DataAnalysis;

import com.company.Wallet;

public class CryptoAnalysis implements Runnable {

    private final CryptoPrices cryptoPrices;
    private final int n;
    private final Wallet wallet;
    private boolean trade;
    String[] crypto = {"Bitcoin", "Ethereum", "TWT", "XRP"};


    public CryptoAnalysis(int n, CryptoPrices cryptoPrices, Wallet wallet){
        this.wallet = wallet;
        this.n = n;
        this.cryptoPrices = cryptoPrices;
    }



    private float getAverage(){
        float averagePrice ;

        if(cryptoPrices.cryptoPrices.get(n).size() > 5){
            float sum = 0f;
            int size = cryptoPrices.cryptoPrices.get(n).size();
            for(int i = 0;i < size;i++){
                sum += cryptoPrices.cryptoPrices.get(n).get(i);
            }
            averagePrice = sum / size;
            return averagePrice;
        }else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getAverage();
        }
    }

    private void buy(){
        while(cryptoPrices.cryptoPrices.get(n).size() < 5){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int size = cryptoPrices.cryptoPrices.get(n).size();

        float currentBalance = wallet.getBalance();
        float currentPrice = cryptoPrices.cryptoPrices.get(n).get(size-1);
        float moneySpent = currentBalance/2;
        float averagePrice = getAverage();

        if(currentPrice/averagePrice < 0.99967){
            float amountForPurchase = moneySpent / cryptoPrices.cryptoPrices.get(n).get(size-1);

            wallet.setCurrencyAmount(n, amountForPurchase);
            wallet.setBalance(currentBalance - moneySpent);
            wallet.setBuyPrice(n, currentPrice);
            System.out.println("\nYou purchased " + crypto[n] +" for amount: " + amountForPurchase + " with price " + currentPrice + " average "+ averagePrice + " money spent "+ moneySpent);
            wallet.printGeneralBalance();
            sell(currentPrice);
        }else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buy();
        }
    }

    private void sell(float buyedPrice){
        int size = cryptoPrices.cryptoPrices.get(n).size();
        float currentPrice = cryptoPrices.cryptoPrices.get(n).get(size-1);
        float currentAmount = wallet.getCurrencyAmount(n);

        if(currentPrice / buyedPrice > 1.0003){
            float availableinUSDT = currentAmount * currentPrice;
            wallet.setCurrencyAmount(n, 0);
            wallet.addUSDT(availableinUSDT);
            wallet.setSellPrice(n, currentPrice);
            System.out.println("\nYou sold " + currentAmount + " of crypto " + crypto[n] + " for price " + availableinUSDT + " with price " + currentPrice);
            wallet.printGeneralBalance();
        }
        else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sell(buyedPrice);
        }
        buy();
    }

    public void setTrade(boolean trade){
        this.trade = trade;
    }

    public boolean getTrade(){
        return trade;
    }


    @Override
    public synchronized void run() {
        buy();
    }
}
