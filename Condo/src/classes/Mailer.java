/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
    private static final String SMTP_HOST = "smtp.mailgun.org";
    private static final String SMTP_LOGIN = "postmaster@sandbox856fddec5e1e48d299f9507b8ec2574d.mailgun.org";
    private static final String FROM_NAME = "Condominium";
    private static final String SMTP_PASSWORD = "88e6fc9e4c0163e869d968df6950526c";
    
   public static boolean send(String email,String text,String subject) throws UnsupportedEncodingException {
                
      // Assuming you are sending email through relay.jangosmtp.net
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", SMTP_HOST);
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.ssl.trust", SMTP_HOST);

      // Get the Session object.
      Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_LOGIN, SMTP_PASSWORD);
            }
        });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(SMTP_LOGIN, FROM_NAME));
         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(email));
         // Set Subject: header field
         message.setSubject(subject);
         // Now set the actual message
         message.setText(text);
         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
      return true;
   }
}