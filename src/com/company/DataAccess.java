package com.company;

import com.company.DataAnalysis.*;
import com.company.Threads.ThreadsForGettingData.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataAccess {

    private static List<String> cryptocurrencies = Arrays.asList("BTC", "ETH", "TWT", "XRP");

    public static void run(CryptoPrices cryptoPrices, Wallet wallet){
        {
            /*
                   Threads for getting the data
             */

            GeneralThread btc = new GeneralThread("BTC", cryptoPrices);
            GeneralThread eth = new GeneralThread("ETH", cryptoPrices);
            GeneralThread twt = new GeneralThread("TWT", cryptoPrices);
            GeneralThread xrp = new GeneralThread("XRP", cryptoPrices);

            Thread threadBTC = new Thread(btc);
            Thread threadETH = new Thread(eth);
            Thread threadTWT = new Thread(twt);
            Thread threadXRP = new Thread(xrp);

            threadBTC.start();
            threadETH.start();
            threadTWT.start();
            threadXRP.start();

        }

        {
            /*
                Threads for analysing the data
             */
            CryptoAnalysis btcAnalysis = new CryptoAnalysis(0, cryptoPrices, wallet);
            Thread btcThread = new Thread(btcAnalysis);
            btcThread.start();

            CryptoAnalysis ethAnalysis = new CryptoAnalysis(1, cryptoPrices, wallet);
            Thread ethThread = new Thread(ethAnalysis);
            ethThread.start();

            CryptoAnalysis twtAnalysis = new CryptoAnalysis(2, cryptoPrices, wallet);
            Thread twtThread = new Thread(twtAnalysis);
            twtThread.start();

            CryptoAnalysis xrpAnalysis = new CryptoAnalysis(3, cryptoPrices, wallet);
            Thread xrpThread = new Thread(xrpAnalysis);
            xrpThread.start();

        }

    }


    public static Map<String, Float> getBTCDataString() {
        Request requestBTC = new Request.Builder()
                .url("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT")
                .get()
                .addHeader("x-mbx-apikey", "aoZC9eQt0Hnpz1JNd0uHLg6qFYYstA1HPA2HjXQrzlotZ3btMqw9fNh8CN3jFlWP")
                .build();

        return getDataMap(getCurrencyData(requestBTC));
    }

    public static Map<String, Float> getETHDataString() {
        Request requestBTC = new Request.Builder()
                .url("https://api.binance.com/api/v3/ticker/price?symbol=ETHUSDT")
                .get()
                .addHeader("x-mbx-apikey", "aoZC9eQt0Hnpz1JNd0uHLg6qFYYstA1HPA2HjXQrzlotZ3btMqw9fNh8CN3jFlWP")
                .build();

        return getDataMap(getCurrencyData(requestBTC));
    }

    public static Map<String, Float> getTWTDataString() {
        Request requestBTC = new Request.Builder()
                .url("https://api.binance.com/api/v3/ticker/price?symbol=TWTUSDT")
                .get()
                .addHeader("x-mbx-apikey", "aoZC9eQt0Hnpz1JNd0uHLg6qFYYstA1HPA2HjXQrzlotZ3btMqw9fNh8CN3jFlWP")
                .build();

        return getDataMap(getCurrencyData(requestBTC));
    }

    public static Map<String, Float> getXRPDataString() {
        Request requestBTC = new Request.Builder()
                .url("https://api.binance.com/api/v3/ticker/price?symbol=XRPUSDT")
                .get()
                .addHeader("x-mbx-apikey", "aoZC9eQt0Hnpz1JNd0uHLg6qFYYstA1HPA2HjXQrzlotZ3btMqw9fNh8CN3jFlWP")
                .build();

        return getDataMap(getCurrencyData(requestBTC));
    }

    private static Map<String, Float> getDataMap(String stringData) {
        Pattern pattern = Pattern.compile("\"symbol\":\"(.*)\",\"price\":\"(.*)\"");
        Matcher matcher = pattern.matcher(stringData);
        String currencyName = "";
        float currencyPrice = 0f;
        if (matcher.find()){
            currencyName = matcher.group(1);
            currencyPrice = Float.parseFloat(matcher.group(2));
        }

        return Map.of(currencyName,currencyPrice);
    }

    private static String getCurrencyData(Request request) {
        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}


