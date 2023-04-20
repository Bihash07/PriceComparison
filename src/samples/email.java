package samples;

import org.testng.annotations.Test;
import utils.Assets;
import utils.EmailAssets;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static utils.EmailAssets.to_mail;

public class email {


    @Test(priority = 1)
    public static void test13_sendAutoMail() {

        // Mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", EmailAssets.host);
        props.put("mail.smtp.port", EmailAssets.port);
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create session with authentication
        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailAssets.username, EmailAssets.password);
            }
        });
        try {

            // Create a new message
            Message message = new MimeMessage(session); // Create a default MimeMessage object.
            message.setFrom(new InternetAddress(EmailAssets.from_mail)); // Set From: header field of the header.


            String[] to_mailList = to_mail.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[to_mailList.length];
            int counter = 0;
            for (String tomail : to_mailList ) {
                recipientAddress[counter] = new InternetAddress(tomail.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);

            // Adding CC recipients


            message.setSubject(EmailAssets.emailSubject); // Set Subject: header field

            // Create a multipart message to hold the text and signature
            Multipart multipart = new MimeMultipart(); // Create a multipart message

            // Add the text part of the message
            BodyPart bodyPart = new MimeBodyPart(); // Create the message part
            bodyPart.setText(EmailAssets.emailBody); // Now set the actual message
            multipart.addBodyPart(bodyPart); // Set text message part


            // Set the multipart as the content of the message
            message.setContent(multipart);  // Send the complete message parts
            Transport.send(message); // Send message

            System.out.println( Assets.Green + "Email sent successfully" + Assets.Blue +
                    " from " + Assets.Yellow + EmailAssets.from_mail + Assets.Blue +
                    " to " + Assets.Yellow + to_mail /* + Blue +
                    "\n CC " + Yellow + Assets.cc_recipient*/);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String [] args ){

        email.test13_sendAutoMail();
    }


}
