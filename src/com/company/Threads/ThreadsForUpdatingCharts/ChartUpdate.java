package com.company.Threads.ThreadsForUpdatingCharts;


import com.company.DataAnalysis.*;
import com.company.GUI.RealTimeChart;
import org.jfree.chart.ChartPanel;


import javax.swing.*;
import java.util.Date;

public class ChartUpdate implements Runnable{
    private final RealTimeChart chart;
    private final String name;
    private final CryptoPrices cryptoPrices;
    public ChartUpdate(String name, RealTimeChart chart, CryptoPrices cryptoPrices){
        this.name = name;
        this.chart = chart;
        this.cryptoPrices = cryptoPrices;
    }

    @Override
    public synchronized void run() {
        while (true) {
            switch (name) {
                case "Bitcoin" -> {
                    if(cryptoPrices.cryptoPrices.get(0).size() > 0)
                    chart.setSeries((double) new Date().getTime(),
                            cryptoPrices.getCryptoPrices(0));
                }
                case "Ethereum" -> {
                    if(cryptoPrices.cryptoPrices.get(1).size() > 0)
                    chart.setSeries((double) new Date().getTime(),
                            cryptoPrices.getCryptoPrices(1));
                }
                case "TWT" -> {
                    if(cryptoPrices.cryptoPrices.get(2).size() > 0)
                    chart.setSeries((double) new Date().getTime(),
                            cryptoPrices.getCryptoPrices(2));
                }
                case "XRP" -> {
                    if(cryptoPrices.cryptoPrices.get(3).size() > 0)
                    chart.setSeries((double) new Date().getTime(),
                            cryptoPrices.getCryptoPrices(3));
                }
            }

            chart.setSeriesCollection(chart.getSeries());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
