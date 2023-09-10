package com.kawishika.util;

import javafx.scene.control.Alert;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.UnknownHostException;
import java.util.Properties;

public class Mail {
    private static Mail mail = null;
    private final String username = "hnkmalwenna@gmail.com";
    private final String password = "kpfr nnek inyz ubqy";
    private Session session = null;

    private Mail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
    }

    public static Mail getInstance() {
        return mail == null ? mail = new Mail() : mail;
    }

    public void sendMail(String to, String subject, String msg) {
        try {

            Message message = new MimeMessage(session);


            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(msg);


            Transport.send(message);

        } catch (MessagingException e) {
           new Alert(Alert.AlertType.ERROR,"Error While Sending Mail !").show();
        }
    }
}
