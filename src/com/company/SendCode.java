package com.company;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendCode {

    String from, password, subject, body, to;
    Properties props;
    Session session;
    public SendCode(String from, String password, String to,  String subject, String body){
        this.from = from;
        this.password = password;
        this.to = to;
        this.subject = subject;
        this.body = body;
        props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        sendMessage();
    }

    private void sendMessage(){
        try {
            Message message = new MimeMessage(session);

            // Set the sender and recipient of the message
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set the subject and body of the message
            message.setSubject(subject);
            message.setText(body);


            // Send the email
            Transport.send(message);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


}
