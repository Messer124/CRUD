package ru.learningApp.CRUD.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Properties;

@Service
public class MailService {

    public void sendEmailToUser(String mail, String message) {
        sendEmail(mail, message);
        System.out.println("Mail request sent to " + mail);
    }

    public void sendGreetingEmail(String mail) {
        sendEmail(mail, "Glad to see you " + mail  + ". Welcome to out site!");
        System.out.println("Greeting request sent to " + mail);
    }

    public void sendEmailOnDeletion(String mail) {
        sendEmail(mail, "It is sad that you go off so fast, " + mail  + ". Goodbye!");
        System.out.println("Deletion request sent to  " + mail);
    }

    public void sendEmailOnUpdating(String mail) {
        sendEmail(mail, "Your profile has been updated!");
        System.out.println("Updating request sent to " + mail);
    }
    public String sendEmail(String mail, String msg) {
        String from = "sex-1994@mail.ru";
        String pass = "Pj20LvJzhBG68G3x3pJR";
        String[] to = { mail }; // list of recipient email addresses
        String subject = "Java send mail example";

        Properties props = System.getProperties();
        String host = "smtp.mail.ru";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(msg);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            System.out.println("eMail was not sent! Error - " + e);
            return "eMail was not sent!";
        }
        return "eMail sent successfully!";
    }
}
