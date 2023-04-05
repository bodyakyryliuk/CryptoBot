package com.company.GUI;


import com.company.DataAnalysis.CryptoPrices;
import com.company.Threads.ThreadsForUpdatingCharts.ChartUpdate;
import com.company.User;
import com.company.Wallet;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainGUI extends JDialog{
    private JFrame jFrame = new JFrame("Crypto Robot 1.0");
    JPanel jPanelBTC, jPanelETH, jPanelTWT, jPanelXRP, jPanelJList;
    public JLabel currentPrice, buyPrice, sellPrice, change;
    private CryptoPrices cryptoPrices;
    private Wallet wallet;
    private String email;

    public MainGUI(CryptoPrices cryptoPrices, Wallet wallet, String email){
        this.email = email;
        this.cryptoPrices = cryptoPrices;
        this.wallet = wallet;
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                User user = new User();
                user.saveUserData(wallet.getBalance(), wallet.getCurrencyAmount(0), wallet.getCurrencyAmount(1),
                        wallet.getCurrencyAmount(2), wallet.getCurrencyAmount(3), email);
            }
        });
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        currentPrice = new JLabel();
        buyPrice = new JLabel();
        sellPrice = new JLabel();
        change = new JLabel();

        addJPanels();

        createCharts();

        createInfoLabels();

        switchPanels();

        jFrame.revalidate();

        jFrame.setVisible(true);
    }

    private void switchPanels(){
        JPanel panels = new JPanel(new CardLayout());
        panels.add(jPanelBTC, "BTC");
        panels.add(jPanelETH, "ETH");
        panels.add(jPanelTWT, "TWT");
        panels.add(jPanelXRP, "XRP");

        addJList(panels);

        jFrame.add(panels, BorderLayout.SOUTH);
    }

    private void addJList(JPanel panels){
        JList<String> list = new JList<>(new String[] {"BTC", "ETH", "TWT", "XRP"});
        list.setVisibleRowCount(-1);
        list.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,17));
        list.setFixedCellWidth(131);
        list.setPreferredSize(new Dimension(600,100));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        list.addListSelectionListener(e -> {
            CardLayout cl = (CardLayout)(panels.getLayout());
            cl.show(panels, list.getSelectedValue());
        });

        //center the text in a cell
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        jFrame.add(list, BorderLayout.NORTH);
    }

    private void createCharts(){
        long initialTime = System.currentTimeMillis();

        RealTimeChart btcChart = new RealTimeChart("Bitcoin", jPanelBTC, cryptoPrices);
        btcChart.updateChart(btcChart);

        RealTimeChart ethChart = new RealTimeChart("Ethereum", jPanelETH, cryptoPrices);
        ethChart.updateChart(ethChart);

        RealTimeChart twtChart = new RealTimeChart("TWT", jPanelTWT, cryptoPrices);
        twtChart.updateChart(twtChart);

        RealTimeChart xrpChart = new RealTimeChart("XRP", jPanelXRP, cryptoPrices);
        xrpChart.updateChart(xrpChart);

        System.out.println("Charts creating time is : " + (System.currentTimeMillis() - initialTime) / 100);
    }

    public void createInfoLabels(){
        InfoPanel btcInfo = new InfoPanel(0, jPanelBTC, "Bitcoin", cryptoPrices, wallet);
        btcInfo.updateInfo();

        InfoPanel ethInfo = new InfoPanel(1, jPanelETH, "Ethereum", cryptoPrices, wallet);
        ethInfo.updateInfo();

        InfoPanel twtInfo = new InfoPanel(2, jPanelTWT, "TWT", cryptoPrices, wallet);
        twtInfo.updateInfo();

        InfoPanel xrpInfo = new InfoPanel(3, jPanelXRP, "XRP", cryptoPrices, wallet);
        xrpInfo.updateInfo();
    }


    private void addJPanels(){
        jPanelBTC = new JPanel();
        jPanelBTC.setLayout(new BorderLayout());

        jPanelETH = new JPanel();
        jPanelETH.setLayout(new BorderLayout());

        jPanelTWT = new JPanel();
        jPanelTWT.setLayout(new BorderLayout());

        jPanelXRP = new JPanel();
        jPanelXRP.setLayout(new BorderLayout());

        jPanelJList = new JPanel();
        jPanelJList.setLayout(new BorderLayout());

    }


}
