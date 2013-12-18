package main.java.de.studichat.chatserver.util;


import main.java.de.studichat.chatserver.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    private static final Log log = LogFactory.getLog(Mail.class);

    public static void sendMail(String recipient, String subject, String emailmessage){



        // Email Data
        final String username = Config.EMAIL_USERNAME;
        final String password = Config.EMAIL_PASSWORD;

        Properties props = new Properties();
        props.put("mail.smtp.auth", Config.EMAIL_AUTH);
        props.put("mail.smtp.starttls.enable", Config.EMAIL_TLS);
        props.put("mail.smtp.host", Config.EMAIL_HOSTNAME);
        props.put("mail.smtp.port", Config.EMAIL_PORT);

        // Email Host Auth
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Config.EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(emailmessage);

            Transport.send(message);
            log.info("->Email wurde verschickt");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }






}