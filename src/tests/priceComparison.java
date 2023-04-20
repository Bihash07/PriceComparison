package tests;

import com.beust.ah.A;
import com.opencsv.CSVWriter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.util.RetryAnalyzerCount;
import utils.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static utils.AppRunner.pickmeDriver;
import static utils.AppRunner.uberDriver;


public class priceComparison {


//    // Create two instances for each device
//    public AndroidDriver pickmeDriver, uberDriver;

//    public String uberTukPrice1,uberTukPrice2,uberTukPrice3,uberTukPrice4,uberTukPrice5,uberTukPrice6,uberTukPrice7,uberTukPrice8,uberTukPrice9,uberTukPrice10;
//    public String pickmeTukPrice1,pickmeTukPrice2,pickmeTukPrice3,pickmeTukPrice4,pickmeTukPrice5,pickmeTukPrice6,pickmeTukPrice7,pickmeTukPrice8,pickmeTukPrice9,pickmeTukPrice10;
//    public String Time1,Time2,Time3,Time4,Time5,Time6,Time7,Time8,Time9,Time10;
//

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
    @Test(priority = 1)
    public void test1() throws Exception {

        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_01);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_01);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_01);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_01);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        WebElement pickmeTukPriceTxt1 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice1 = pickmeTukPriceTxt1.getText();
        System.out.println("pass");
        WebElement pickmeTukWithoutDisTxt1 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT);
        String pickmeTukWithoutDis1 = pickmeTukWithoutDisTxt1.getText();
        System.out.println("pass");
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt1 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice1 = uberTukPriceTxt1.getText();
        System.out.println("pass");
        WebElement uberTukWithoutDisTxt1 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT);
        String uberTukWithoutDis1 = uberTukWithoutDisTxt1.getText();
        System.out.println("pass");
        

        LocalDateTime now = LocalDateTime.now();
        String Time1 = now.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice1 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis1 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_01 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_01 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time1 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice1 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis1 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_01 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_01 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time1 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        AppRunner.closeApps();
        AppRunner.openApp();

//        Function.backBtn();
//
//        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//        AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
////        AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//
//        Thread.sleep(3000);
//    }
//
//    @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_02);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_02);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_02);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_02);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt2 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice2 = uberTukPriceTxt2.getText();
        WebElement uberTukWithoutDisTxt2 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis2 = uberTukWithoutDisTxt2.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt2 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice2 = pickmeTukPriceTxt2.getText();
        WebElement pickmeTukWithoutDisTxt2 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis2 = pickmeTukWithoutDisTxt2.getText();

        LocalDateTime now2 = LocalDateTime.now();
        String Time2 = now2.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice2 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis2 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_02 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_02 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time2 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice2 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis2 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_02 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_02 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time2 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_03);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_03);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_03);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_03);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt3 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice3 = uberTukPriceTxt3.getText();
        WebElement uberTukWithoutDisTxt3 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis3 = uberTukWithoutDisTxt3.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt3 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice3 = pickmeTukPriceTxt3.getText();
        WebElement pickmeTukWithoutDisTxt3 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis3 = pickmeTukWithoutDisTxt3.getText();

        LocalDateTime now3 = LocalDateTime.now();
        String Time3 = now3.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice3 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis3 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_03 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_03 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time3 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice3 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis3 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_03 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_03 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time3 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();

//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_04);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_04);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_04);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_04);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt4 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice4 = uberTukPriceTxt4.getText();
        WebElement uberTukWithoutDisTxt4 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis4 = uberTukWithoutDisTxt4.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt4 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice4 = pickmeTukPriceTxt4.getText();
        WebElement pickmeTukWithoutDisTxt4 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis4 = pickmeTukWithoutDisTxt4.getText();

        LocalDateTime now4 = LocalDateTime.now();
        String Time4 = now4.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice4 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis4 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_04 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_04 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time4 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice4 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis4 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_04 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_04 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time4 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();

//        Thread.sleep(2000);

        AppRunner.closeApps();
        AppRunner.openApp();
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_05);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_05);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_05);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_05);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt5 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice5 = uberTukPriceTxt5.getText();
        WebElement uberTukWithoutDisTxt5 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis5 = uberTukWithoutDisTxt5.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt5 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice5 = pickmeTukPriceTxt5.getText();
        WebElement pickmeTukWithoutDisTxt5 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis5 = pickmeTukWithoutDisTxt5.getText();

        LocalDateTime now5 = LocalDateTime.now();
        String Time5 = now5.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice5 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis5 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_05 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_05 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time5 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice5 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis5 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_05 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_05 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time5 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();
//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {




        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_06);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_06);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_06);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_06);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt6 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice6 = uberTukPriceTxt6.getText();
        WebElement uberTukWithoutDisTxt6 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis6 = uberTukWithoutDisTxt6.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement pickmeTukPriceTxt6 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice6 = pickmeTukPriceTxt6.getText();
        WebElement pickmeTukWithoutDisTxt6 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis6 = pickmeTukWithoutDisTxt6.getText();

        LocalDateTime now6 = LocalDateTime.now();
        String Time6 = now6.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice6 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis6 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_06 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_06 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time6 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice6 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis6 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_06 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_06 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time6 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();
//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_07);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_07);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_07);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_07);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt7 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice7 = uberTukPriceTxt7.getText();
        WebElement uberTukWithoutDisTxt7 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis7 = uberTukWithoutDisTxt7.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt7 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice7 = pickmeTukPriceTxt7.getText();
        WebElement pickmeTukWithoutDisTxt7 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis7 = pickmeTukWithoutDisTxt7.getText();

        LocalDateTime now7 = LocalDateTime.now();
        String Time7 = now7.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice7 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis7 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_07 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_07 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time7 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice7 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis7 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_07 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_07 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time7 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();
//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_08);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_08);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_08);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_08);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt8 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice8 = uberTukPriceTxt8.getText();
        WebElement uberTukWithoutDisTxt8 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis8 = uberTukWithoutDisTxt8.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt8 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice8 = pickmeTukPriceTxt8.getText();
        WebElement pickmeTukWithoutDisTxt8 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis8 = pickmeTukWithoutDisTxt8.getText();

        LocalDateTime now8 = LocalDateTime.now();
        String Time8 = now8.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice8 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis8 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_08 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_08 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time8 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice8 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis8 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_08 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_08 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time8 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();
        AppRunner.closeApps();
        AppRunner.openApp();

//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_09);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_09);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_09);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_09);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt9 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice9 = uberTukPriceTxt9.getText();
        WebElement uberTukWithoutDisTxt9 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis9 = uberTukWithoutDisTxt9.getText();
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt9 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice9 = pickmeTukPriceTxt9.getText();
        WebElement pickmeTukWithoutDisTxt9 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE_WITHOUT_DISCOUNT );
        String pickmeTukWithoutDis9 = pickmeTukWithoutDisTxt9.getText();

        LocalDateTime now9 = LocalDateTime.now();
        String Time9 = now9.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice9 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis9 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_09 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_09 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time9 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice9 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis9 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_09 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_09 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time9 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        Function.backBtn();

        AppRunner.closeApps();
        AppRunner.openApp();
//        Thread.sleep(2000);
//    }
//        @Test(priority = 2)
//    public void test2() throws Exception {


        Function.clickRideBtn();
        Function.searchPickupLocation();
        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_10);
        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Locations.PICKUP_LOCATION_10);
        Function.spaceAndHideKeyboard();
        Function.clickPickupLocationBar();
        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_10);
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Locations.DROP_LOCATION_10);
        Function.spaceAndHideKeyboard();
        Function.clickDropLocationBar();

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt10 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice10 = uberTukPriceTxt10.getText();
        WebElement uberTukWithoutDisTxt10 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis10 = uberTukWithoutDisTxt10.getText();


        pickmeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement pickmeTukPriceTxt10 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukPrice10 = pickmeTukPriceTxt10.getText();
        WebElement pickmeTukWithoutDisTxt10 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
        String pickmeTukWithoutDis10 = pickmeTukPriceTxt10.getText();

        LocalDateTime now10 = LocalDateTime.now();
        String Time10 = now10.format(Time);

        System.out.println(Assets.Blue +
                "PriceList >> \n"+
                " Uber Tuk price: " + Assets.Yellow + uberTukPrice10 + Assets.Blue + ".\n " +
                " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis10 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_10 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_10 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time10 + ". \n" + Assets.Blue + "\n" +
                " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice10 + Assets.Blue + ".\n " +
                " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis10 + Assets.Blue + ".\n " +
                "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_10 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_10 + Assets.Blue + ".\n " +
                "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
                "Time: " + Assets.Yellow + Time10 + ".\n");

        uberDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        pickmeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        Function.backBtn();

        Thread.sleep(2000);
//    }


//    @Test(priority = 3)
//    public void test11_createCSV() throws Exception{

        // Create a CSVWriter object
        CSVWriter csvWriter = new CSVWriter(new FileWriter(Assets.csvFile, StandardCharsets.UTF_8),
                ',', '"', CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        // Define column headers
        String[] headers = {"Distance(KM)", "PickUp Location", "Drop Location", "Date", "Time", "Uber Tuk price", "Uber Tuk WithoutDiscount",  "Pickme Tuk price", "Pickme Tuk WithDiscount"};

        // Write headers to CSV file
        csvWriter.writeNext(headers);


        String[] row1 = {"1 KM", Locations.PICKUP_LOCATION_01 , Locations.DROP_LOCATION_01, Assets.date, Time1,uberTukPrice1, uberTukWithoutDis1, pickmeTukPrice1, pickmeTukWithoutDis1};
        String[] row2 = {"2 KM", Locations.PICKUP_LOCATION_02, Locations.DROP_LOCATION_02, Assets.date, Time2, uberTukPrice2, uberTukWithoutDis2, pickmeTukPrice2, pickmeTukWithoutDis2};
        String[] row3 = {"3 KM", Locations.PICKUP_LOCATION_03, Locations.DROP_LOCATION_03, Assets.date, Time3, uberTukPrice3, uberTukWithoutDis3, pickmeTukPrice3, pickmeTukWithoutDis3};
        String[] row4 = {"4 KM", Locations.PICKUP_LOCATION_04, Locations.DROP_LOCATION_04, Assets.date, Time4, uberTukPrice4, uberTukWithoutDis4, pickmeTukPrice4, pickmeTukWithoutDis4};
        String[] row5 = {"5 KM", Locations.PICKUP_LOCATION_05, Locations.DROP_LOCATION_05, Assets.date, Time5, uberTukPrice5, uberTukWithoutDis5, pickmeTukPrice5, pickmeTukWithoutDis5};
        String[] row6 = {"6 KM", Locations.PICKUP_LOCATION_06, Locations.DROP_LOCATION_06, Assets.date, Time6, uberTukPrice6, uberTukWithoutDis6, pickmeTukPrice6, pickmeTukWithoutDis6};
        String[] row7 = {"7 KM", Locations.PICKUP_LOCATION_07, Locations.DROP_LOCATION_07, Assets.date, Time7, uberTukPrice7, uberTukWithoutDis7, pickmeTukPrice7, pickmeTukWithoutDis7};
        String[] row8 = {"8 KM", Locations.PICKUP_LOCATION_08, Locations.DROP_LOCATION_08, Assets.date, Time8, uberTukPrice8, uberTukWithoutDis8, pickmeTukPrice8, pickmeTukWithoutDis8};
        String[] row9 = {"9 KM", Locations.PICKUP_LOCATION_09, Locations.DROP_LOCATION_09, Assets.date, Time9, uberTukPrice9, uberTukWithoutDis9, pickmeTukPrice9, pickmeTukWithoutDis9};
        String[] row10 = {"10 KM", Locations.PICKUP_LOCATION_10, Locations.DROP_LOCATION_10, Assets.date , Time10, uberTukPrice10, uberTukWithoutDis10, pickmeTukPrice10, pickmeTukWithoutDis10};

        // Write data rows to CSV file
        csvWriter.writeNext(row1);
        csvWriter.writeNext(row2);
        csvWriter.writeNext(row3);
        csvWriter.writeNext(row4);
        csvWriter.writeNext(row5);
        csvWriter.writeNext(row6);
        csvWriter.writeNext(row7);
        csvWriter.writeNext(row8);
        csvWriter.writeNext(row9);
        csvWriter.writeNext(row10);

        // Close CSV writer
        csvWriter.close();

        System.out.println(Assets.Magenta + "Data written to " + Assets.Yellow + Assets.csvFile);
    }

    @Test(priority = 4)
    public void test12_copyCsvFile() {

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

            System.out.println( Assets.Cyan + "File copied to destination folder: " + Assets.Yellow + destinationFile.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to copy file.");
            e.printStackTrace();
        }

    }

    @Test(priority = 5)
    public void test13_sendAutoMail() {

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

            String[] to_mailList = EmailAssets.to_mail.split(",");

            InternetAddress[] recipientAddress = new InternetAddress[to_mailList.length];
            int counter = 0;
            for (String tomail : to_mailList ) {
                recipientAddress[counter] = new InternetAddress(tomail.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);


            String[] cc_mailList = EmailAssets.cc_recipient.split(",");

//            InternetAddress[] ccRecipientAddress = new InternetAddress[cc_mailList.length];
//            for (String ccmail : cc_mailList ) {
//                ccRecipientAddress[counter] = new InternetAddress(ccmail.trim());
//                counter++;
//            }
//            message.setRecipients(Message.RecipientType.CC, ccRecipientAddress);

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

            System.out.println( Assets.Green + "Email sent successfully" + Assets.Blue +
                    " from " + Assets.Yellow + EmailAssets.from_mail + Assets.Blue +
                    " to " + Assets.Yellow + EmailAssets.to_mail /* + Blue +
                    "\n CC " + Yellow + Assets.cc_recipient*/);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 6)
    public void Test14_deleteCsvFile() throws Exception{
        // Convert the file path to Path object
        Path path = Paths.get(Assets.csvFile);

        // Delete the file
        Files.delete(path);
        System.out.println( Assets.Red + "File deleted: " + Assets.Yellow + Assets.csvFile);

        AppRunner.tearDown();
    }

}
