/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.MailEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ouertani
 */
public class JavaMialutil {

    public static void sendMail(String recepient, String code) throws Exception {
        System.out.println("preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "aymen.ouertani@esprit.tn";
        String password = "193JMT2693";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }

        });
        Message message = prepareMessage(session, myAccountEmail, recepient, code);
        System.out.println(message);
        Transport.send(message);
        System.out.println("message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String code) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("your account new password");
            System.out.println("we are here email");
            message.setText(code);
            return message;
        } catch (Exception ex) {

        }
        return null;
    }
}
