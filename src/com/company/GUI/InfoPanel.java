package com.company.GUI;

import com.company.DataAnalysis.CryptoPrices;
import com.company.Threads.ThreadsForUpdatingCharts.InfoUpdate;
import com.company.Wallet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JFrame{
    private JLabel priceLabel, currentAmountLabel, changeLabel, buyPriceLabel, walletInfo, currentBTC, currentETH, currentTWT, currentXRP;
    private JToggleButton jToggleButton;
    private String name;
    private JPanel panel;
    private CryptoPrices cryptoPrices;
    private Wallet wallet;
    private int n;


    public InfoPanel(int n, JPanel panel, String name, CryptoPrices cryptoPrices, Wallet wallet){
        this.name = name;
        this.panel = panel;
        this.cryptoPrices = cryptoPrices;
        this.n = n;
        this.wallet = wallet;

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setPreferredSize(new Dimension(286,500));

        labelPanel.setBackground(Color.WHITE);

        addLabels(labelPanel);

        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(labelPanel, BorderLayout.SOUTH);

        JPanel jPanel1 = new JPanel(new BorderLayout());
        jPanel1.add(jPanel, BorderLayout.SOUTH);

        panel.add(jPanel, BorderLayout.EAST);
    }

    private void addLabels(JPanel labelPanel){
        createLabels();

        labelPanel.add(priceLabel);
        labelPanel.add(currentAmountLabel);
        labelPanel.add(buyPriceLabel);
        labelPanel.add(changeLabel);
        labelPanel.add(walletInfo);
        labelPanel.add(currentBTC);
        labelPanel.add(currentETH);
        labelPanel.add(currentTWT);
        labelPanel.add(currentXRP);

        setLabels();
        labelPanel.repaint();
    }




    private void createLabels(){
        priceLabel = new JLabel();
        priceLabel.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
        priceLabel.setText("Current price: ");

        currentAmountLabel = new JLabel();
        currentAmountLabel.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
        currentAmountLabel.setText("Current amount: ");

        changeLabel = new JLabel();
        changeLabel.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
        changeLabel.setText("Change: ");

        buyPriceLabel = new JLabel();
        buyPriceLabel.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
        buyPriceLabel.setText("Buy price");

        walletInfo = new JLabel();
        walletInfo.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));

        currentBTC = new JLabel();
        currentBTC.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));

        currentETH = new JLabel();
        currentETH.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));

        currentTWT = new JLabel();
        currentTWT.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));

        currentXRP = new JLabel();
        currentXRP.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,15));
    }

    private void setLabels(){
        priceLabel.setBounds(15,80,250,25);
        currentAmountLabel.setBounds(15,105,250,25);
        buyPriceLabel.setBounds(15,130,250,25);
        changeLabel.setBounds(15,155,250,25);
        walletInfo.setBounds(15,180,250,25);
        currentBTC.setBounds(15,205,250,25);
        currentETH.setBounds(15,230,250,25);
        currentTWT.setBounds(15,255,250,25);
        currentXRP.setBounds(15,280,250,25);
    }

    public void setPriceLabelText(String text){
        this.priceLabel.setText(text);
    }

    public void setCurrentAmountLabelText(String text){
        this.currentAmountLabel.setText(text);
    }

    public void setChangeLabelText(String text){
        this.changeLabel.setText(text);
    }

    public void setWalletInfoText(String text){
        this.walletInfo.setText(text);
    }

    public void setBuyPriceLabelText(String text){
        this.buyPriceLabel.setText(text);
    }

    public void setCurrentBTCLabel(String text){
        this.currentBTC.setText(text);
    }

    public void setCurrentETHLabel(String text){
        this.currentETH.setText(text);
    }

    public void setCurrentTWTLabel(String text){
        this.currentTWT.setText(text);
    }

    public void setCurrentXRPLabel(String text){
        this.currentXRP.setText(text);
    }

    public void updateInfo(){
        InfoUpdate infoUpdate = new InfoUpdate(n, name, panel, cryptoPrices, wallet);
        Thread thread = new Thread(infoUpdate);
        thread.start();
    }


}
