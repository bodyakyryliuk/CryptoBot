package com.company.GUI;

import com.company.SendCode;
import com.company.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLData;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends JDialog{
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel nameLabel;
    private JLabel sournameLabel;
    private JTextField nameField;
    private JTextField sournameField;
    private JTextField emailField;
    private JLabel repPasswordLabel;
    private JButton sendCodeButton;
    private JButton cancelRegistrationButton;
    private JButton registrationButton;
    private JPanel registrationPanel;
    private JLabel confirmationCodeLabel;
    private JTextField confirmationCodeField;
    private JLabel codeSentLabel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPanel authorizationPanel;
    private JFrame jFrame;
    private String code = getActivationCode();

    public Registration(JFrame jFrame){
        super(jFrame);
        this.jFrame = jFrame;
        setTitle("Registration");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(registrationPanel);
        setMinimumSize(new Dimension(400,500));
        setModal(true);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        passwordField1.setEchoChar('*');
        passwordField2.setEchoChar('*');
        setConfirmationCode(false);

        cancelRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelRegistration();
            }
        });

        sendCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkAllFields()) {
                    if(sendCodeButton.getText().equals("Send confirmation code")) {
                        setConfirmationCode(true);
                        new SendCode("aldkddd123123@gmail.com", "npyuyuyycrwbdsge", emailField.getText(), "Activation code", code);
                    }else {
                        registerUser(code);
                    }
                }
            }
        });

        setVisible(true);
    }

    private void addUser(){
        User user = new User();
        user.addUser(nameField.getText(), sournameField.getText(), emailField.getText(), passwordField1.getText(),
                3000, 0, 0, 0, 0);
    }


    private void registerUser(String code){
        setConfirmationCode(true);
        if(confirmationCodeField.getText().equals(code)) {
            dispose();
            addUser();
            new Authorization(null);
        }
    }

    private String getActivationCode(){
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            password.append(random.nextInt(10));
        }
        return password.toString();
    }

    private void setConfirmationCode(boolean enabled){
        confirmationCodeLabel.setVisible(enabled);
        confirmationCodeField.setVisible(enabled);
        codeSentLabel.setVisible(enabled);

        if(enabled)
            sendCodeButton.setText("Sign up");

    }

    private void cancelRegistration(){
        dispose();
        new Authorization(null);
    }

    private boolean checkAllFields(){
        String name = nameField.getText();
        String sourname = sournameField.getText();
        String email = emailField.getText();
        String password = passwordField1.getText();
        String repPassword = passwordField2.getText();

        if(checkName(name) && checkSourname(sourname) && checkEmail(email)&&
           checkPassword(password) && checkRepPassword())
            return true;
        else return false;
    }

    private boolean checkRepPassword(){
        if(passwordField1.getText().equals(passwordField2.getText()))
            return true;
        else {
            passwordField2.setBackground(Color.RED);
            return false;
        }
    }

    private boolean checkName(String name){
        Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.matches())
            nameField.setBackground(Color.RED);
        else
            nameField.setBackground(Color.WHITE);
        return matcher.matches();
    }

    private boolean checkSourname(String name){
        Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.matches())
            sournameField.setBackground(Color.RED);
        else
            sournameField.setBackground(Color.WHITE);
        return matcher.matches();
    }

    private boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = pattern.matcher(email);
        User user = new User();

        if(!matcher.matches() || user.checkIfExists(email) ) {
            emailField.setBackground(Color.RED);
            return false;
        }
        else {
            emailField.setBackground(Color.WHITE);
            return true;
        }
    }

    private boolean checkPassword(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);

        if(!matcher.matches())
            passwordField1.setBackground(Color.RED);
        else
            passwordField1.setBackground(Color.WHITE);

        return matcher.matches();
    }

}
