package com.company.GUI;

import com.company.DataAccess;

import com.company.DataAnalysis.CryptoPrices;
import com.company.Threads.ThreadsForUpdatingCharts.ChartUpdate;
import com.company.Wallet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
    Class for creating a line chart that updates at real time
    It's a general class that can be used for currently 4 cryptocurrencies
 */

public class RealTimeChart extends JFrame {
    private XYSeries series;
    private XYSeriesCollection seriesCollection = new XYSeriesCollection();
    private CryptoPrices cryptoPrices;
    ChartPanel chartPanel;
    String title;
    JPanel jPanel;

    public RealTimeChart(String title, JPanel jPanel, CryptoPrices cryptoPrices) {
        long initialTime = System.currentTimeMillis();
        this.title = title;
        this.jPanel = new JPanel();
        this.cryptoPrices = cryptoPrices;

        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Time",
                "Price",
                seriesCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        DateAxis xAxis = new DateAxis("Time");
        xAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        chart.getXYPlot().setDomainAxis(xAxis);

        ChartPanel chartPanel = createChartPanel(chart);
        ValueAxis yAxis = chart.getXYPlot().getRangeAxis();
        series = new XYSeries(title);

        setYAxis(yAxis);


        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(chartPanel,BorderLayout.SOUTH);
        jPanel.add(jPanel1,BorderLayout.WEST);



        System.out.println("Constructor time : " + (System.currentTimeMillis() - initialTime) / 100);
    }

    private ChartPanel createChartPanel(JFreeChart chart){
        long initialTime = System.currentTimeMillis();
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500,500));
        chartPanel.setLayout(new BorderLayout());
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        System.out.println("createChartPanel time is : " + (System.currentTimeMillis() - initialTime) / 100);
        return chartPanel;
    }

    public String getTitle(){
        return title;
    }

    public void setSeriesCollection(XYSeries series){
        seriesCollection.removeAllSeries();
        seriesCollection.addSeries(series);
        chartPanel.repaint();
    }

    private void setYAxis(ValueAxis yAxis){
        long initialTime = System.currentTimeMillis();

        switch (title) {
            case "Bitcoin"-> {
                yAxis.setRange(cryptoPrices.getCryptoPrices(0) / 1.003, cryptoPrices.getCryptoPrices(0) * 1.003);
                setSeries((double) new Date().getTime(), cryptoPrices.getCryptoPrices(0));
            }
            case "Ethereum"-> {
                yAxis.setRange(cryptoPrices.getCryptoPrices(1) / 1.003, cryptoPrices.getCryptoPrices(1) * 1.003);
                setSeries((double) new Date().getTime(), cryptoPrices.getCryptoPrices(1));
            }
            case "TWT"-> {
                yAxis.setRange(cryptoPrices.getCryptoPrices(2) / 1.003, cryptoPrices.getCryptoPrices(2) * 1.003);
                setSeries((double) new Date().getTime(), cryptoPrices.getCryptoPrices(2));
            }
            case "XRP"-> {
                yAxis.setRange(cryptoPrices.getCryptoPrices(3) / 1.003, cryptoPrices.getCryptoPrices(3) * 1.003);
                setSeries((double) new Date().getTime(), cryptoPrices.getCryptoPrices(3));
            }
        }
        System.out.println("SetYAxis time is : " + (System.currentTimeMillis() - initialTime) / 100);
    }


    public void updateChart(RealTimeChart chart){
        ChartUpdate chartUpdate = new ChartUpdate(title, chart, cryptoPrices);
        Thread thread = new Thread(chartUpdate);
        thread.start();
    }

    public void setSeries(double x, double y){
        series.add(x,y);
    }

    public XYSeries getSeries(){
        return series;
    }

}
