package com.ca.chorus.builder;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    private static final String SMTP_HOST_NAME = "smtp1.ca.com";


    public void test(String serrorline, String mailid) throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "false");

        Session mailSession = Session.getDefaultInstance(props);
        
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("Chorus Local Build Failure");
        message.setContent(serrorline, "text/html");
        message.setFrom(new InternetAddress("ChorusBuilderNotification@ca.com"));
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress(mailid));

        transport.connect();
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}