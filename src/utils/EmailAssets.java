package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EmailAssets {

    // Get the current timestamp
    public static final LocalDateTime now = LocalDateTime.now();
    public static final String dateTime = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy  hh:mm a"));

    // SMTP server settings
    public static final String username = "no-reply@pickme.lk"; //change accordingly
    public static final String password = "gvcijmbcgeebxwad"; //change accordingly


    public static final String host = "smtp.gmail.com"; //change accordingly
    public static final int port = 587; //change accordingly


    // Sender email addresses
    public static final String from_mail = "no-reply@pickme.lk"; //change accordingly

    // recipient email addresses
    public static final String to_mail = "zulfer@pickme.lk ,sandaru.lorensuhewa@pickme.lk ," +
                                         "manthila.weerasinghe@pickme.lk ,kalpani@pickme.lk ," +
                                         "janith.gunawardhana@pickme.lk ,bihash.arulampalam@pickme.lk ";

    // cc_recipient email addresses
    public static final String cc_recipient = "";

    // email Subject
    public static final String emailSubject = "Pickme & Uber Price comparison on "+ dateTime;

    // email Body
    public static final String emailBody = """
            Dear all,

            Please find the attachment of Price comparison for the Uber & Pickme.""";


}

