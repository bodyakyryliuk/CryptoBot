package com.company;

import com.company.GUI.Authorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;

public class RunApplication extends JDialog {
    private static Authorization authorization;

    public static void run(){
        if(checkInternet())
            authorization = new Authorization(null);
        else addNoInternetDialog();
    }

    private static void addNoInternetDialog(){
        JDialog noInternedDialog = new JDialog();
        noInternedDialog.setLayout(new BorderLayout());
        noInternedDialog.setPreferredSize(new Dimension(300, 150));
        noInternedDialog.pack();
        noInternedDialog.setLocationRelativeTo(null);

        JLabel label = new JLabel("No internet connection");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,17));

        JButton jButton = new JButton("Retry");
        jButton.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,17));
        jButton.setSize(new Dimension(100, 100));

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInternet())
                    authorization = new Authorization(null);
            }
        });

        noInternedDialog.add(label, BorderLayout.NORTH);
        noInternedDialog.add(jButton, BorderLayout.SOUTH);
        noInternedDialog.setVisible(true);
    }

    private static boolean checkInternet(){
        boolean internet = false;
        try {
            internet = InetAddress.getByName("google.com").isReachable(1000);
        } catch (IOException e) {
            System.out.println(e);
        }
        return internet;
    }
}
