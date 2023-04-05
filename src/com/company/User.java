package com.company;

import com.company.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Connection connection = null;
    private Statement statement = null;

    public User(){
        makeConnection();
    }

    private void makeConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userssql", "root", "root");
            statement = connection.createStatement();



        } catch (SQLException a) {
            a.printStackTrace();
        }
    }

    public void addUser(String name, String sourname, String email, String password,
                        float usdtAmount, float btcAmount, float ethAmount, float twtAmount, float xrpAmount){
        try {
            String data = "INSERT INTO user (name, sourname, email, password, usdt, btc, eth, twt, xrp) Values(" +
                    "'" + name + "', '" + sourname + "', '" + email + "', '" + password + "', '"+ usdtAmount + "', '"
                    + btcAmount + "', '" + ethAmount + "', '" + twtAmount + "', '" + xrpAmount +"')";

            statement.execute(data);

         //   connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkIfExists(String enteredEmail){
        boolean exists = false;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

            while (resultSet.next()){
                String email = resultSet.getString("email");
                if(email.equals(enteredEmail))
                    exists = true;
            }

            //connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return exists;
    }

    public void saveUserData(float usdt, float btc, float eth, float twt, float xrp, String email){
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE user SET usdt = ?, btc = ?, eth = ?, twt = ?, xrp = ? WHERE email = ?");
            stmt.setFloat(1, usdt);
            stmt.setFloat(2, btc);
            stmt.setFloat(3, eth);
            stmt.setFloat(4, twt);
            stmt.setFloat(5, xrp);
            stmt.setString(6, email);

            stmt.executeUpdate();

            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Float> getWalletInfo(String email){
        List<Float> walletInfo = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT usdt, btc, eth, twt, xrp FROM user WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                walletInfo.add(resultSet.getFloat("usdt"));
                walletInfo.add(resultSet.getFloat("btc"));
                walletInfo.add(resultSet.getFloat("eth"));
                walletInfo.add(resultSet.getFloat("twt"));
                walletInfo.add(resultSet.getFloat("xrp"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return walletInfo;
    }

    public boolean checkPassword(String email, String enteredPassword){
        boolean correct = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM user WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String password = resultSet.getString("password");
                correct = enteredPassword.equals(password);
            }

            //connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return correct;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWallet(Wallet wallet){

        // PreparedStatement stmt = con.prepareStatement("UPDATE tablename SET column1 = ?, column2 = ? WHERE id = ?");

        //PreparedStatement statement = connection.prepareStatement("UPDATE user SET USDT");
    }


}
