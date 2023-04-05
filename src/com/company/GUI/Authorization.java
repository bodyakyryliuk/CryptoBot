package com.company.GUI;

import com.company.DataAccess;
import com.company.DataAnalysis.CryptoPrices;
import com.company.User;
import com.company.Wallet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authorization extends JDialog{
    private JTextField emailField;
    private JButton loginButton;
    private JButton registrationButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel authorizationLabel;
    public JPanel authorizationPanel;
    private JPasswordField passwordField;
    private JFrame jFrame;
    private User user;

    public Authorization(JFrame authorizationFrame){
        super(authorizationFrame);
        setTitle("Authorization");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(authorizationPanel);
        setMinimumSize(new Dimension(400,500));
        setModal(true);
        setLocationRelativeTo(authorizationFrame);
        setResizable(false);
        passwordField.setEchoChar('*');
        user = new User();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationForm();
            }
        });

        setVisible(true);
    }


    private void loginUser(){
        if(user.checkPassword(emailField.getText(), passwordField.getText()))
            runApplication();
    }


    private void openRegistrationForm(){
        dispose();
        new Registration(null);
    }

    private void runApplication(){
        List<Float> walletInfo = new ArrayList<>(user.getWalletInfo(emailField.getText()));
        user.closeConnection();
        Wallet wallet = new Wallet(walletInfo.get(0), walletInfo.get(1), walletInfo.get(2), walletInfo.get(3), walletInfo.get(4));
        CryptoPrices cryptoPrices = new CryptoPrices();
        DataAccess.run(cryptoPrices, wallet);
        dispose();
        new MainGUI(cryptoPrices, wallet, emailField.getText());
    }



}
