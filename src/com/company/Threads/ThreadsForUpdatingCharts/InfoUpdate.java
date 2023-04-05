package com.company.Threads.ThreadsForUpdatingCharts;

import com.company.DataAccess;
import com.company.DataAnalysis.*;
import com.company.GUI.InfoPanel;
import com.company.Wallet;

import javax.swing.*;


public class InfoUpdate implements Runnable {
    private final InfoPanel infoPanel;
    private final int n;
    private final CryptoPrices cryptoPrices;
    private final Wallet wallet;

    public InfoUpdate(int n, String name, JPanel panel, CryptoPrices cryptoPrices, Wallet wallet) {
        this.n = n;
        this.cryptoPrices = cryptoPrices;
        this.wallet = wallet;
        infoPanel = new InfoPanel(n, panel, name, cryptoPrices, wallet);
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (cryptoPrices.cryptoPrices.get(n).size() > 0) {
                float currentPrice = cryptoPrices.getCryptoPrices(n);
                infoPanel.setPriceLabelText("Current price: " + currentPrice);
                infoPanel.setCurrentAmountLabelText("Current amount: " + wallet.getCurrencyAmount(n));

                float buyPrice = wallet.getBuyPrice(n);
                if (buyPrice != 0) {
                    infoPanel.setBuyPriceLabelText("Buy price: " + buyPrice);
                    float change = 1 - (buyPrice / currentPrice);
                    infoPanel.setChangeLabelText("Change: " + change);
                }
                infoPanel.setWalletInfoText("Balance: " + wallet.getBalance());
                infoPanel.setCurrentBTCLabel("BTC: " + wallet.getCurrencyAmount(0));
                infoPanel.setCurrentETHLabel("ETH: " + wallet.getCurrencyAmount(1));
                infoPanel.setCurrentTWTLabel("TWT: " + wallet.getCurrencyAmount(2));
                infoPanel.setCurrentXRPLabel("XRP: " + wallet.getCurrencyAmount(3));
                infoPanel.repaint();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

