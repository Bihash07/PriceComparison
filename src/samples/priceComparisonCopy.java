//package samples;
//
//
//import com.beust.ah.A;
//import com.opencsv.CSVWriter;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.lang.reflect.Array;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import utils.*;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//
//
//public class priceComparisonCopy {
//
//
//    // Create two instances for each device
//    public AndroidDriver pickmeDriver, uberDriver;
//
//
//    // Get the current time
//    LocalTime currentTime = LocalTime.now();
//
//    // Format the time using a DateTimeFormatter
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//    String time = currentTime.format(formatter);
//
//    String Red = "\u001b[31m";
//    String Green = "\u001B[32m";
//    String Yellow = "\u001b[33m";
//    String Blue = "\u001b[34m";
//    String Magenta = "\u001b[35m";
//    String Cyan = "\u001b[36m";
//
//    DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    DateTimeFormatter Time = DateTimeFormatter.ofPattern("hh:mm:ss a");
//
//
//
//    @BeforeTest
//    public void setUp() throws Exception {
//
//
//
//        AppiumServer1.startServer();
//        AppiumServer2.startServer();
//
//
//        // Set DesiredCapabilities for Pickme app to send to the appium Server
//        DesiredCapabilities pickmeCap = new DesiredCapabilities();
//        pickmeCap.setCapability("platformName","Android" );
//        pickmeCap.setCapability("deviceName","A13");
//        pickmeCap.setCapability("udid", "RF8TB0F3P0A");
//        pickmeCap.setCapability("platformVersion", "13");
//        pickmeCap.setCapability("appPackage", "com.pickme.passenger");
//        pickmeCap.setCapability("appActivity", "com.pickme.passenger.feature.account.presentation.LandingActivity");
//        pickmeCap.setCapability("noReset", true);
//        pickmeCap.setCapability("ensureWebviewsHavePages", true);
//        pickmeCap.setCapability("nativeWebScreenshot", true);
//        pickmeCap.setCapability("connectHardwareKeyboard", true);
//
//        // Set DesiredCapabilities for Uber app to send to the appium Server
//        DesiredCapabilities uberCap = new DesiredCapabilities();
//        uberCap.setCapability("platformName", "Android");
//        uberCap.setCapability("deviceName", "M02");
//        uberCap.setCapability("udid", "R58R63FKHSB");
//        uberCap.setCapability("platformVersion", "11");
//        uberCap.setCapability("appPackage", "com.ubercab");
//        uberCap.setCapability("appActivity", "com.ubercab.presidio.app.core.root.RootActivity");
//        uberCap.setCapability("noReset", true);
//        uberCap.setCapability("ensureWebviewsHavePages", true);
//        uberCap.setCapability("nativeWebScreenshot", true);
//        uberCap.setCapability("connectHardwareKeyboard", true);
//
//
//        // Create AppiumDriver instances for each device
//        // Initialize driver 2 for Uber App
//        uberDriver = new AndroidDriver(new URL ("http://localhost:4724/wd/hub"), uberCap);
//        System.out.println( "Uber App was launched.");
//
//        pickmeDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), pickmeCap);
//        System.out.println("Pickme App was launched.");
//
//        Thread.sleep(5000);
//
////        WebDriverWait wait = new WebDriverWait(uberDriver, Duration.ofSeconds(3));
////        wait.until(ExpectedConditions.visibilityOfElementLocated(ElementLocator.UBER_RIDE_BUTTON));
////
////        // Wait for the page to be loaded
////        wait.until(ExpectedConditions.urlContains("pageUrl"));
//    }
//
//
////    @Test(dataProvider = "inputProvider")
////    public void enterTextTest(String input) {
////        enterText(input);
////    }
////
////    public void enterText(String input) {
////        WebElement element = pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT);
////        element.sendKeys(input);
////    }
////
////    @DataProvider(name = "inputProvider")
////    public Object[] getInputs() {
////        return new Object[]{
////                Assets.DROP_LOCATION_01,
////                Assets.DROP_LOCATION_02,
////                Assets.DROP_LOCATION_03,
////                Assets.DROP_LOCATION_04,
////                Assets.DROP_LOCATION_05,
////                Assets.DROP_LOCATION_06,
////                Assets.DROP_LOCATION_07,
////                Assets.DROP_LOCATION_08,
////                Assets.DROP_LOCATION_09,
////                Assets.DROP_LOCATION_10
////        };
////     }
//
//    // -- Automation Test -- //
//    @Test(priority = 1)
//    public void test1() throws Exception {
//
//        // Format the time using a DateTimeFormatter
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//        // Get the current time
//        LocalTime currentTime = LocalTime.now();
//        String Time = currentTime.format(formatter);
//
//        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
//        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
//        el1.click();
//        el1.click();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_01);
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_01);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
//
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_01);
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_01);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
//
//        WebElement visibleElement = null;
//        List<By> locators = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators) {
//            List<WebElement> elements = uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement = element;
//                    break;
//                }
//            }
//            if (visibleElement != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice1 = "";
//        if (visibleElement != null) {
//            // get the text of the visible element
//            uberTukPrice1 = visibleElement.getText();
//        }
//
//        WebElement pickmeTukPriceTxt1 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice1 = pickmeTukPriceTxt1.getText();
//
//        System.out.println(Blue +
//                "PriceList >> \n"+
//                " Uber Tuk price: " + Yellow + uberTukPrice1 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_01 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_01 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ". \n" + Blue + "\n" +
//                " Pickme Tuk price: " + Yellow + pickmeTukPrice1 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_01 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_01 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ".\n");
//
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//
//        Thread.sleep(3000);
//    }
//
//    @Test(priority = 2)
//    public void test2() throws Exception {
//
//        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
//        WebElement el2 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
//        el2.click();
//        el2.click();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_02);
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_02);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
//
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_02);
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_02);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
//
//
//        WebElement visibleElement2 = null;
//        List<By> locators2 = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators2 ) {
//            List<WebElement> elements = uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement2 = element;
//                    break;
//                }
//            }
//            if (visibleElement2 != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice2 = "";
//        if (visibleElement2 != null) {
//            // get the text of the visible element
//            uberTukPrice2 = visibleElement2.getText();
//        }
//
//        WebElement pickmeTukPriceTxt2 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice2 = pickmeTukPriceTxt2.getText();
//
//
//        // Get the current time
//        LocalTime currentTime2 = LocalTime.now();
//
//        // Format the time using a DateTimeFormatter
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss a");
//        String Time2 = currentTime.format(formatter);
//
//        System.out.println(Blue +
//                "PriceList >> \n"+
//                " Uber Tuk price: " + Yellow + uberTukPrice2 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_02 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_02 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time2 + ". \n" + Blue + "\n" +
//                " Pickme Tuk price: " + Yellow + pickmeTukPrice2 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_02 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_02 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time2 + ".\n");
//
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 3)
//    public void test3() throws Exception {
//
//        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
//        WebElement el3 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
//        el3.click();
//        el3.click();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_03);
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_03);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
//
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_03);
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_03);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
//
//        WebElement visibleElement3 = null;
//        List<By> locators3 = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators3 ) {
//            List<WebElement> elements = uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement3 = element;
//                    break;
//                }
//            }
//            if (visibleElement3 != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice3 = "";
//        if (visibleElement3 != null) {
//            // get the text of the visible element
//            uberTukPrice3 = visibleElement3.getText();
//        }
//
//        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice3 = pickmeTukPriceTxt.getText();
//
//        System.out.println(Blue +
//                "PriceList >> \n"+
//                " Uber Tuk price: " + Yellow + uberTukPrice3 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_03 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_03 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ". \n" + Blue + "\n" +
//                " Pickme Tuk price: " + Yellow + pickmeTukPrice3 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_03 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_03 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ".\n");
//
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 4)
//    public void test4() throws Exception {
//
//        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
//        WebElement el4 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
//        el4.click();
//        el4.click();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_04);
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_04);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
//
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_04);
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_04);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
//
//        WebElement visibleElement4 = null;
//        List<By> locators4 = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators4 ) {
//            List<WebElement> elements = uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement4 = element;
//                    break;
//                }
//            }
//            if (visibleElement4 != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice4 = "";
//        if (visibleElement4 != null) {
//            // get the text of the visible element
//            uberTukPrice4 = visibleElement4.getText();
//        }
//
//        WebElement pickmeTukPriceTxt4 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice4 = pickmeTukPriceTxt4.getText();
//
//        LocalDateTime now = LocalDateTime.now();
//
//        System.out.println(Blue +
//                "PriceList >> \n"+
//                " Uber Tuk price: " + Yellow + uberTukPrice4 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_04 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_04 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ". \n" + Blue + "\n" +
//                " Pickme Tuk price: " + Yellow + pickmeTukPrice4 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_04 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_04 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ".\n");
//
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//
//
//        Thread.sleep(2000);
//    }
//
//    @Test(priority = 5)
//    public void test5() throws Exception {
//
//        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
//        WebElement el5 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
//        el5.click();
//        el5.click();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_05);
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_05);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
//
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_05);
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_05);
//        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
//        uberDriver.hideKeyboard();
//        pickmeDriver.hideKeyboard();
//
//        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
//        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
//
//        WebElement visibleElement5 = null;
//        List<By> locators5 = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators5 ) {
//            List<WebElement> elements = uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement5 = element;
//                    break;
//                }
//            }
//            if (visibleElement5 != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice5 = "";
//        if (visibleElement5 != null) {
//            // get the text of the visible element
//            uberTukPrice5 = visibleElement5.getText();
//        }
//
//        WebElement pickmeTukPriceTxt5 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice5 = pickmeTukPriceTxt5.getText();
//
//        System.out.println(Blue +
//                "PriceList >> \n"+
//                " Uber Tuk price: " + Yellow + uberTukPrice5 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_05 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_05 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ". \n" + Blue + "\n" +
//                " Pickme Tuk price: " + Yellow + pickmeTukPrice5 + Blue + ".\n " +
//                "Location: " + Yellow + Assets.PICKUP_LOCATION_05 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_05 + Blue + ".\n " +
//                "Date: " + Yellow + Assets.date + Blue + ".\n " +
//                "Time: " + Yellow + Time + ".\n");
//
//        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
//        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
//        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
//
//    }
//
//    @Test(priority = 6)
//    public void test6() throws Exception {
////
////        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
////        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
////        el1.click();
////        el1.click();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_06);
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_06);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
////
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_06);
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_06);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
////
////        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE);
////        String uberTukPrice6 = uberTukPriceTxt.getText();
////
////        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
////        String pickmeTukPrice6 = pickmeTukPriceTxt.getText();
////
////        System.out.println(Blue +
////                "PriceList >> \n"+
////                " Uber Tuk price: " + Yellow + uberTukPrice6 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_06 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_06 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ". \n" + Blue + "\n" +
////                " Pickme Tuk price: " + Yellow + pickmeTukPrice6 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_06 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_06 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ".\n");
////
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
////
////
////        Thread.sleep(2000);
////    }
////
////    @Test(priority = 7)
////    public void test7() throws Exception {
////
////        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
////        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
////        el1.click();
////        el1.click();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_07);
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_07);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
////
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_07);
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_07);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
////
////        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE);
////        String uberTukPrice7 = uberTukPriceTxt.getText();
////
////        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
////        String pickmeTukPrice7 = pickmeTukPriceTxt.getText();
////
////        System.out.println(Blue +
////                "PriceList >> \n"+
////                " Uber Tuk price: " + Yellow + uberTukPrice7 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_07 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_07 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ". \n" + Blue + "\n" +
////                " Pickme Tuk price: " + Yellow + pickmeTukPrice7 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_07 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_07 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ".\n");
////
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
////
////
////        Thread.sleep(2000);
////    }
////
////    @Test(priority = 8)
////    public void test8() throws Exception {
////
////        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
////        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
////        el1.click();
////        el1.click();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_08);
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_08);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
////
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_08);
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_08);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
////
////        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE);
////        String uberTukPrice8 = uberTukPriceTxt.getText();
////
////        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
////        String pickmeTukPrice8 = pickmeTukPriceTxt.getText();
////
////        System.out.println(Blue +
////                "PriceList >> \n"+
////                " Uber Tuk price: " + Yellow + uberTukPrice8 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_08 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_08 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ". \n" + Blue + "\n" +
////                " Pickme Tuk price: " + Yellow + pickmeTukPrice8 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_08 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_08 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ".\n");
////
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
////
////
////        Thread.sleep(2000);
////    }
////
////    @Test(priority = 9)
////    public void test9() throws Exception {
////
////        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
////        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
////        el1.click();
////        el1.click();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_09);
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_09);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
////
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_09);
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_09);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
////
////        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE);
////        String uberTukPrice9 = uberTukPriceTxt.getText();
////
////        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
////        String pickmeTukPrice9 = pickmeTukPriceTxt.getText();
////
////        System.out.println(Blue +
////                "PriceList >> \n"+
////                " Uber Tuk price: " + Yellow + uberTukPrice9 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_09 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_09 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ". \n" + Blue + "\n" +
////                " Pickme Tuk price: " + Yellow + pickmeTukPrice9 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_09 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_09 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ".\n");
////
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON).click();
////        uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
////
////
////        Thread.sleep(2000);
////    }
//
////    @Test(priority = 10)
////    public void test10() throws Exception {
////
////        uberDriver.findElement(ElementLocator.UBER_RIDE_BUTTON).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_RIDE_BUTTON).click();
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_SEARCH).click();
////        WebElement el1 = uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_SEARCH);
////        el1.click();
////        el1.click();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_10);
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_INPUT).sendKeys(Assets.PICKUP_LOCATION_10);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_PICKUP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_PICKUP_LOCATION_BAR).click();
////
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_10);
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_SEARCH).click();
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_INPUT).sendKeys(Assets.DROP_LOCATION_10);
////        uberDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        pickmeDriver.pressKey(new io.appium.java_client.android.nativekey.KeyEvent(AndroidKey.SPACE));
////        uberDriver.hideKeyboard();
////        pickmeDriver.hideKeyboard();
////
////        pickmeDriver.findElement(ElementLocator.PICKME_DROP_LOCATION_BAR).click();
////        uberDriver.findElement(ElementLocator.UBER_DROP_LOCATION_BAR).click();
////
////        WebElement uberTukPriceTxt = uberDriver.findElement(ElementLocator.UBER_PRICE);
////        String uberTukPrice10 = uberTukPriceTxt.getText();
////
////        WebElement pickmeTukPriceTxt = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
////        String pickmeTukPrice10 = pickmeTukPriceTxt.getText();
////
////        System.out.println(Blue +
////                "PriceList >> \n"+
////                " Uber Tuk price: " + Yellow + uberTukPrice10 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_10 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_10 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ". \n" + Blue + "\n" +
////                " Pickme Tuk price: " + Yellow + pickmeTukPrice10 + Blue + ".\n " +
////                "Location: " + Yellow + Assets.PICKUP_LOCATION_10 + Blue + "  to " + Yellow + Assets.DROP_LOCATION_10 + Blue + ".\n " +
////                "Date: " + Yellow + Assets.date + Blue + ".\n " +
////                "Time: " + Yellow + Assets.time + ".\n");
////
////        uberDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////        pickmeDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
////
////    }
//
////
////    @Test (priority = 11)
////    public void test11_createCSV() throws Exception{
//
//        // Create a CSVWriter object
//        CSVWriter csvWriter = new CSVWriter(new FileWriter(Assets.csvFile, StandardCharsets.UTF_8),
//                ',', '"', CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//
//        // Define column headers
//        String[] headers = {"Distance(KM)", "PickUp Location", "Drop Location", "Date", "Time", "Uber Tuk price", "Pickme Tuk price"};
//
//        // Write headers to CSV file
//        csvWriter.writeNext(headers);
//
//
////        // Define data rows
////       List <String[]> row = new ArrayList<>();
////
////
////        for (String row : row ){
////            // Write data rows to CSV file
////            csvWriter.writeNext(row);
////        }
////
////        String[] row1 = {"1", Assets.PICKUP_LOCATION_01 , Assets.DROP_LOCATION_01, Assets.date, Time1,uberTukPrice1, pickmeTukPrice1};
////        String[] row2 = {"2", Assets.PICKUP_LOCATION_02, Assets.DROP_LOCATION_02, Assets.date, Time2, uberTukPrice2, pickmeTukPrice2};
////        String[] row3 = {"3", Assets.PICKUP_LOCATION_03, Assets.DROP_LOCATION_03, Assets.date, Time3, uberTukPrice3, pickmeTukPrice3};
////        String[] row4 = {"4", Assets.PICKUP_LOCATION_04, Assets.DROP_LOCATION_04, Assets.date, Time4,  uberTukPrice4, pickmeTukPrice4};
////        String[] row5 = {"5", Assets.PICKUP_LOCATION_05, Assets.DROP_LOCATION_05, Assets.date, Time5, uberTukPrice5, pickmeTukPrice5};
////        String[] row6 = {"6", Assets.PICKUP_LOCATION_06, Assets.DROP_LOCATION_06, Assets.date, Time1, uberTukPrice6, pickmeTukPrice6};
////        String[] row7 = {"7", Assets.PICKUP_LOCATION_07, Assets.DROP_LOCATION_07, Assets.date, time, uberTukPrice7, pickmeTukPrice7};
////        String[] row8 = {"8", Assets.PICKUP_LOCATION_08, Assets.DROP_LOCATION_08, Assets.date, time, uberTukPrice8, pickmeTukPrice8};
////        String[] row9 = {"9", Assets.PICKUP_LOCATION_09, Assets.DROP_LOCATION_09, Assets.date, time, uberTukPrice9, pickmeTukPrice9};
////        String[] row10 = {"10", Assets.PICKUP_LOCATION_10, Assets.DROP_LOCATION_10, Assets.date ,time, uberTukPrice10, pickmeTukPrice10};
//
//        // Write data rows to CSV file
////        csvWriter.writeNext(row1);
////        csvWriter.writeNext(row2);
////        csvWriter.writeNext(row3);
////        csvWriter.writeNext(row4);
////        csvWriter.writeNext(row5);
////        csvWriter.writeNext(row6);
////        csvWriter.writeNext(row7);
////        csvWriter.writeNext(row8);
////        csvWriter.writeNext(row9);
////        csvWriter.writeNext(row10);
//
//        // Close CSV writer
//        csvWriter.close();
//
//        System.out.println( Magenta + "Data written to " + Yellow + Assets.csvFile);
//    }
//
//
//    @Test(priority = 12)
//    public void test12_copyCsvFile() {
//
//        try {
//            // Create a File object for the source file
//            File sourceFile = new File(Assets.csvFile);
//
//            // Create a File object for the destination folder
//            File destinationDir = new File(Assets.destinationFolder);
//
//            // Create the destination folder if it does not exist
//            if (!destinationDir.exists()) {
//                destinationDir.mkdirs();
//            }
//
//            // Create a File object for the destination file
//            File destinationFile = new File(destinationDir, Assets.csvFile);
//
//            // Copy the source file to the destination file
//            Files.copy(sourceFile.toPath(),destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            System.out.println( Cyan + "File copied to destination folder: " + Yellow + destinationFile.getAbsolutePath());
//
//        } catch (Exception e) {
//            System.out.println("Failed to copy file.");
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test(priority = 13)
//    public void test13_sendAutoMail() {
//
//        // Mail properties
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", EmailAssets.host);
//        props.put("mail.smtp.port", EmailAssets.port);
//        props.put("mail.smtp.ssl.trust", "*");
//        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        // Create session with authentication
//        // Get the Session object.
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(EmailAssets.username, EmailAssets.password);
//            }
//        });
//        try {
//
//            // Create a new message
//            Message message = new MimeMessage(session); // Create a default MimeMessage object.
//            message.setFrom(new InternetAddress(Assets.from_mail)); // Set From: header field of the header.
//
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Assets.to));
//
////
////            for (String toMail : Assets.to_mail){
////                // Set To: header field of the header.
////                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
////            }
////
////            for (String ccRecipient : Assets.cc_recipient){
////                // Set To: header field of the header.
////                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccRecipient));
////            }
//
//            // Adding CC recipients
//            message.setSubject(Assets.emailSubject); // Set Subject: header field
//
//            // Create a multipart message to hold the text and signature
//            Multipart multipart = new MimeMultipart(); // Create a multipart message
//
//            // Add the text part of the message
//            BodyPart bodyPart = new MimeBodyPart(); // Create the message part
//            bodyPart.setText(Assets.emailBody); // Now set the actual message
//            multipart.addBodyPart(bodyPart); // Set text message part
//
//            // -- File attachment -- //
//            DataSource source = new FileDataSource(Assets.csvFile);
//            bodyPart.setDataHandler(new DataHandler(source));
//            bodyPart.setFileName(Assets.csvFile);
//            multipart.addBodyPart(bodyPart);
//
//            // Set the multipart as the content of the message
//            message.setContent(multipart);  // Send the complete message parts
//            Transport.send(message); // Send message
//
//            System.out.println( Green + "Email sent successfully" + Blue +
//                    " from " + Yellow + Assets.from_mail + Blue +
//                    " to " + Yellow + Assets.to /* + Blue +
//                    "\n CC " + Yellow + Assets.cc_recipient*/);
//
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
////    @Test(priority = 9)
////    public void csvFileCopy() throws Exception{
////        // Specify the source file path and destination folder path
////        String sourceFilePath = csvFile;
////        String destinationFolderPath = "C:\\Users\\Bihash Arulampalam\\Desktop\\Cost_Comparison";
////
////        // Convert the file paths to Path objects
////        Path sourcePath = Paths.get(sourceFilePath);
////        Path destinationPath = Paths.get(destinationFolderPath);
////
////        // Copy the file to the destination folder
////        Files.copy(sourcePath, destinationPath.resolve(sourcePath.getFileName()));
////        System.out.println("File copied from " + sourceFilePath + " to " + destinationFolderPath);
////    }
//
//    @Test(priority = 14)
//    public void Test14_deleteCsvFile() throws Exception{
//        // Convert the file path to Path object
//        Path path = Paths.get(Assets.csvFile);
//
//        // Delete the file
//        Files.delete(path);
//        System.out.println( Red + "File deleted: " + Yellow + Assets.csvFile);
//    }
//
//
//    @AfterTest
//    public void tearDown() {
////        pickmeDriver.stopRecordingScreen();
////        uberDriver.stopRecordingScreen();
//        // Quit both AppiumDriver instances
//        pickmeDriver.quit();
//        uberDriver.quit();
//        AppiumServer1.stopServer();
//        AppiumServer2.stopServer();
//    }
//}
