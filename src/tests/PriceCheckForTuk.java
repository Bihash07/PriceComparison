package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utils.AppRunner.pickmeDriver;
import static utils.AppRunner.uberDriver;
import static utils.EmailAssets.to_mail;
import static utils.Function.copyCsvFile;

public class PriceCheckForTuk {

    // Get the current time
    LocalTime currentTime = LocalTime.now();

    // Format the time using a DateTimeFormatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
    String time = currentTime.format(formatter);

    DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter Time = DateTimeFormatter.ofPattern("hh:mm:ss a");
    
    @BeforeTest
    public void openApps() throws Exception {
        
        AppRunner.openApp();
    
    }

    // -- Automation Test -- //
    
    // GET price From Both App
    @Test(priority = 1 ,dataProvider = "inputData")
    public void priceComparison (String PICKUP_LOCATION ,String DROP_LOCATION) throws Exception {

        Function.clickRideBtn();
        Function.searchPickupLocation();
        Function.inputPickupLocation(PICKUP_LOCATION);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        Function.inputDropLocation(DROP_LOCATION);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        AppRunner.uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();

        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice = uberTukPriceTxt.getText();
        System.out.println(uberTukPrice);

        AppRunner.pickmeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice = pickmeTukPriceTxt.getText();
        System.out.println(pickmeTukPrice);

        AppRunner.uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        AppRunner.pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

        LocalDateTime now = LocalDateTime.now();
        String time = now.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + PICKUP_LOCATION + Assets.Blue + "  to " + Assets.Yellow + DROP_LOCATION + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + time + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + PICKUP_LOCATION + Assets.Blue + "  to " + Assets.Yellow + DROP_LOCATION + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + time + ".\n");

        AppRunner.uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        AppRunner.pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        AppRunner.closeApps();
        AppRunner.openApp();

    }



    // Define a data provider method that returns the getText() values
    @DataProvider(name = "getTextValues")
    public Object[][] getTextValues() {
        Object[][] data = new Object[10][1];
        for (int i = 0; i < 10; i++) {
            WebElement uberTukPriceTxt = new WebDriverWait(pickmeDriver, null)
                    .until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.UBER_PRICE));
            data[i][0] = uberTukPriceTxt;
        }
        return data;
    }

    public void CSVcreate(){
        // Write the getText() values to a CSV file
         try { // Create a file and a CSV writer
             File file = new File(Assets.csvFile);
             FileWriter writer = new FileWriter(file);

             // Write the values to the file
             for (Object[] row : getTextValues()) {
//                 String text = (row[0]);
//                 writer.append(text).append("\n");
             }

             // Close the writer
             writer.flush();
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }


    @Test(priority = 3)
    public void test_copyCsvFile() {

        Function.copyCsvFile();

    }

    @Test(priority = 4)
    public void test13_sendAutoMail() {

        Function.sendAutoMail();

    }

    @Test(priority = 5)
    public void Test14_deleteCsvFile() throws Exception {

        Function.deleteCsvFile();

    }


    @AfterTest
    public void closeApps() throws Exception {

        AppRunner.closeApps();

    }

}
