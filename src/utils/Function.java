package utils;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utils.Assets.*;
import static utils.EmailAssets.*;


public class Function {




    @DataProvider(name = "inputData")
    public Object[][] testData() {
        return new Object[][] {
                {Locations.PICKUP_LOCATION_01,Locations.DROP_LOCATION_01},
                {Locations.PICKUP_LOCATION_02,Locations.DROP_LOCATION_02},
                {Locations.PICKUP_LOCATION_03,Locations.DROP_LOCATION_03},
                {Locations.PICKUP_LOCATION_04,Locations.DROP_LOCATION_04},
                {Locations.PICKUP_LOCATION_05,Locations.DROP_LOCATION_05},
                {Locations.PICKUP_LOCATION_06,Locations.DROP_LOCATION_06},
                {Locations.PICKUP_LOCATION_07,Locations.DROP_LOCATION_07},
                {Locations.PICKUP_LOCATION_08,Locations.DROP_LOCATION_08},
                {Locations.PICKUP_LOCATION_09,Locations.DROP_LOCATION_09},
                {Locations.PICKUP_LOCATION_10,Locations.DROP_LOCATION_10}
        };
    }

    public static void clickRideBtn() {
        AppRunner.uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
        AppRunner.uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
        AppRunner.pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
    }

    public static void searchPickupLocation() {
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
        WebElement pickupSearch = AppRunner.uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
        pickupSearch.click();
        pickupSearch.click();
    }

    public static void inputPickupLocation(String PICKUP_LOCATION) {
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(PICKUP_LOCATION);
        AppRunner.uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(PICKUP_LOCATION);
    }

    public static void inputDropLocation(String DROP_LOCATION) {

        AppRunner.uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(DROP_LOCATION);
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(DROP_LOCATION);
    }

    public static void clickPickupLocationBar() {
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
        AppRunner.uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
    }
    public static void clickDropLocationBar() {
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
        AppRunner.uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
    }
    public static void backBtn() {
        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON3).click();
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
    }

    public static void spaceAndHideKeyboard() {
        AppRunner.uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
        AppRunner.pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
        AppRunner.uberDriver.hideKeyboard();
        AppRunner.pickmeDriver.hideKeyboard();
    }

    public static void getTukPrice(){

        // Start the app and navigate to the main page

    }


    public static void copyCsvFile() {

        try {
            // Create a File object for the source file
            File sourceFile = new File(Assets.csvFile);

            // Create a File object for the destination folder
            File destinationDir = new File(Assets.destinationFolder);

            // Create the destination folder if it does not exist
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            // Create a File object for the destination file
            File destinationFile = new File(destinationDir, Assets.csvFile);

            // Copy the source file to the destination file
            Files.copy(sourceFile.toPath(),destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println( Assets.Cyan + "File copied to destination folder: " + Yellow + destinationFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to copy file.");
            e.printStackTrace();
        }

    }


    public static void sendAutoMail() {

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
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailAssets.username, EmailAssets.password);
            }
        });
        try {

            // Create a new message
            Message message = new MimeMessage(session); // Create a default MimeMessage object.
            message.setFrom(new InternetAddress(from_mail)); // Set From: header field of the header.


            String[] to_mailList = to_mail.split(",");

            InternetAddress[] recipientAddress = new InternetAddress[to_mailList.length];
            int counter = 0;
            for (String tomail : to_mailList ) {
                recipientAddress[counter] = new InternetAddress(tomail.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);


            String[] cc_mailList = cc_recipient.split(",");

            InternetAddress[] ccRecipientAddress = new InternetAddress[cc_mailList.length];
            for (String ccmail : cc_mailList ) {
                ccRecipientAddress[counter] = new InternetAddress(ccmail.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.CC, ccRecipientAddress);


            // Adding CC recipients
            message.setSubject(EmailAssets.emailSubject); // Set Subject: header field

            // Create a multipart message to hold the text and signature
            Multipart multipart = new MimeMultipart(); // Create a multipart message

            // Add the text part of the message
            BodyPart bodyPart = new MimeBodyPart(); // Create the message part
            bodyPart.setText(EmailAssets.emailBody); // Now set the actual message
            multipart.addBodyPart(bodyPart); // Set text message part

            // -- File attachment -- //
            DataSource source = new FileDataSource(Assets.csvFile);
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName(Assets.csvFile);
            multipart.addBodyPart(bodyPart);

            // Set the multipart as the content of the message
            message.setContent(multipart);  // Send the complete message parts
            Transport.send(message); // Send message

            System.out.println( Green + "Email sent successfully" + Blue +
                    " from " + Yellow + from_mail + Blue +
                    " to " + Yellow + to_mail  + Blue +
                    "\n CC " + Yellow + cc_recipient);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteCsvFile() throws Exception{
        // Convert the file path to Path object
        Path path = Paths.get(Assets.csvFile);

        // Delete the file
        Files.delete(path);
        System.out.println( Red + "File deleted: " + Yellow + Assets.csvFile);

        AppRunner.tearDown();
    }



    public static void appHome() {


        // Relaunch the app to go to the home page
//        AppRunner.uberDriver.();


//        // Go to the home page using the back button
//        while (!AppRunner.uberDriver.currentActivity().equals(Assets.UBER_APP_ACTIVITY)) {
//            AppRunner.uberDriver.navigate().back();
//        }
//        while (!AppRunner.pickmeDriver.currentActivity().equals(Assets.PICKME_APP_ACTIVITY)) {
//            AppRunner.pickmeDriver.navigate().back();
//        }


        // Start the app and navigate to the main page
        AppRunner.uberDriver.startActivity(new Activity(Assets.UBER_APP_PACKAGE, Assets.UBER_APP_ACTIVITY));
        AppRunner.pickmeDriver.startActivity(new Activity(Assets.PICKME_APP_PACKAGE, Assets.PICKME_APP_ACTIVITY));


    }
}
