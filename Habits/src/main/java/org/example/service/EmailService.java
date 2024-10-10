package org.example.service;


import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@Setter
public class EmailService {
    private String from;
    private String[] recipients;
    private String subject;
    private String message;
    private boolean debug = false;

    public EmailService(String from, String[] recipients, String subject, String message) {
        this.from = from;
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;

        // Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.jcom.net");

        // Create a session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);

        try {
            Message msg = new MimeMessage(session);

            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            msg.addHeader("Stay on the path", "You should complete your habit today");

            msg.setSubject(subject);
            msg.setContent(message, "text/plain");

            // Send the message
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
