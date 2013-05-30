package org.back.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Alejandro Garcia
 */
public class EnviarMail {

    public static boolean enviarMail(String to, String subject, String msg) {

        try {

            final Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("supermercado.properties"));

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("mail.from")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}