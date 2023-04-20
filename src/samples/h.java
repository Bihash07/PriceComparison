package samples;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.AppRunner.pickmeDriver;
import static utils.AppRunner.uberDriver;

public class h {

//    @Test(invocationCount = 10)
//    public void test() throws Exception{
//        System.out.println("hey");
//        Thread.sleep(3000);
//        // Add your test steps here
//        // ...
//
//        // ...
//        // End of test steps
//    }


    // Get the current time
    LocalTime currentTime = LocalTime.now();

    // Format the time using a DateTimeFormatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
    String time = currentTime.format(formatter);

    DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter Time = DateTimeFormatter.ofPattern("hh:mm:ss a");

    String Red = "\u001b[31m";
    String Green = "\u001B[32m";
    String Yellow = "\u001b[33m";
    String Blue = "\u001b[34m";
    String Magenta = "\u001b[35m";
    String Cyan = "\u001b[36m";


    @BeforeTest
    public static void openApp() throws Exception {

        AppiumServer1.startServer();
        AppiumServer2.startServer();

        // Set DesiredCapabilities for Pickme app to send to the appium Server
        DesiredCapabilities pickmeCap = new DesiredCapabilities();
        pickmeCap.setCapability("platformName", "Android");
        pickmeCap.setCapability("deviceName", "A13");
        pickmeCap.setCapability("udid", "RF8TB0F3P0A");
        pickmeCap.setCapability("platformVersion", "13");
        pickmeCap.setCapability("appPackage", "com.pickme.passenger");
        pickmeCap.setCapability("appActivity", "com.pickme.passenger.feature.account.presentation.LandingActivity");
        pickmeCap.setCapability("noReset", true);
        pickmeCap.setCapability("ensureWebviewsHavePages", true);
        pickmeCap.setCapability("nativeWebScreenshot", true);
        pickmeCap.setCapability("connectHardwareKeyboard", true);
        pickmeCap.setCapability("skipDeviceInitialization",true) ;

        // Set DesiredCapabilities for Uber app to send to the appium Server
        DesiredCapabilities uberCap = new DesiredCapabilities();
        uberCap.setCapability("platformName", "Android");
        uberCap.setCapability("deviceName", "M02");
        uberCap.setCapability("udid", "R58R63FKHSB");
        uberCap.setCapability("platformVersion", "11");
        uberCap.setCapability("appPackage", "com.ubercab");
        uberCap.setCapability("appActivity", "com.ubercab.presidio.app.core.root.RootActivity");
        uberCap.setCapability("noReset", true);
        uberCap.setCapability("ensureWebviewsHavePages", true);
        uberCap.setCapability("nativeWebScreenshot", true);
        uberCap.setCapability("connectHardwareKeyboard", true);


        // Create AppiumDriver instances for each device
        // Initialize driver 2 for Uber App
        uberDriver = new AndroidDriver(new URL("http://localhost:4724/wd/hub"), uberCap);
        System.out.println("Uber App was launched.");

        Thread.sleep(5000);

        pickmeDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), pickmeCap);
        System.out.println("Pickme App was launched.");
        Thread.sleep(10000);
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







        // Assuming there are multiple layouts with class name "my-layout"
        List<WebElement> layouts = pickmeDriver.findElements(By.xpath("//android.widget.RelativeLayout[@resource-id='com.pickme.passenger:id/rlvdv']"));

        // Loop through each layout to find the specific text element
        for (WebElement layout : layouts) {
            // Assuming the reference text element has text "Reference Text"
            WebElement referenceText = layout.findElement(ElementLocator.PICKME_VEHICLE_TUK_TEXT);

            // Assuming the target text element has class name "target-text"
            List<WebElement> targetTexts = layout.findElements(ElementLocator.PICKME_PRICE1);

            // Loop through each potential target text element to find the correct one
            for (WebElement targetText : targetTexts) {
                // Assuming the target text element has text "Target Text"
                if (targetText.findElement(By.xpath(".//span[contains(text(), 'Target Text')]")) != null) {
                    // Do something with the target text element
                    System.out.println(targetText.getText());
                    break; // Break out of the inner loop once the correct target text element is found
                }
            }
        }


//        WebElement visibleElement1 = null;
//        List<By> locators1 = Arrays.asList(ElementLocator.UBER_PRICE1, ElementLocator.UBER_PRICE2, ElementLocator.UBER_PRICE3);
//        for (By locator : locators1) {
//            List<WebElement> elements = AppRunner.uberDriver.findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    // this element is visible, save it and exit the loop
//                    visibleElement1 = element;
//                    break;
//                }
//            }
//            if (visibleElement1 != null) {
//                // we found a visible element, exit the outer loop as well
//                break;
//            }
//        }
//        String uberTukPrice1 = "";
//        if (visibleElement1 != null) {
//            // get the text of the visible element
//            uberTukPrice1 = visibleElement1.getText();
//        }

//        WebElement pickmeTukPriceTxt1 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
//        String pickmeTukPrice1 = pickmeTukPriceTxt1.getText();

//
//        // Set a timeout for element search
//        uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        // Set the search parameters for the unique element
//        WebElement tukName = pickmeDriver.findElement(ElementLocator.PICKME_VEHICLE_CAR);


//        TestBase.swipeElement(pickmeDriver.findElement(ElementLocator.PICKME_VEHICLE_CAR));
//
//
//        // Set the maximum number of swipes to prevent an infinite loop
//        int maxSwipes = 10;
//        int swipes = 0;
//
//        // Swipe until the unique element is found or the maximum number of swipes is reached
//        while (pickmeDriver.findElements(ElementLocator.PICKME_VEHICLE_CAR_TEXT).size() == 0 && swipes < maxSwipes) {
//            (new TouchAction(pickmeDriver))
//                    .press(PointOption.point(804, 1703))
//                    .moveTo(PointOption.point(209, 1711))
//                    .release().perform();
//        }

        // Check if the unique element is found
        WebElement pickmeTukPrice1 = null;
        if (pickmeDriver.findElements(ElementLocator.PICKME_VEHICLE_CAR_TEXT).size() > 0) {
            // Get the unique element and print its text
            System.out.println("good");
            pickmeTukPrice1 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
            System.out.println(pickmeTukPrice1.getText());
        } else {
            System.out.println("The unique element was not found.");
        }

        uberDriver.findElement(ElementLocator.UBER_VEHICLE_TUK_TEXT).click();
        WebElement uberTukPriceTxt1 = uberDriver.findElement(ElementLocator.UBER_PRICE );
        String uberTukPrice1 = uberTukPriceTxt1.getText();
        WebElement uberTukWithoutDisTxt1 = uberDriver.findElement(ElementLocator.UBER_PRICE_WITHOUT_DISCOUNT );
        String uberTukWithoutDis1 = uberTukWithoutDisTxt1.getText();



        LocalDateTime now = LocalDateTime.now();
        String Time1 = now.format(Time);

        System.out.println(Blue +
                "PriceList >> \n" +
                " Uber Tuk price: " + Yellow + uberTukPrice1 + Blue + ".\n " +
                "Location: " + Yellow + Locations.PICKUP_LOCATION_01 + Blue + "  to " + Yellow + Locations.DROP_LOCATION_01 + Blue + ".\n " +
                "Date: " + Yellow + Assets.date + Blue + ".\n " +
                "Time: " + Yellow + Time1 + ". \n" + Blue + "\n" +
                " Pickme Tuk price: " + Yellow + pickmeTukPrice1 + Blue + ".\n " +
                "Location: " + Yellow + Locations.PICKUP_LOCATION_01 + Blue + "  to " + Yellow + Locations.DROP_LOCATION_01 + Blue + ".\n " +
                "Date: " + Yellow + Assets.date + Blue + ".\n " +
                "Time: " + Yellow + Time1 + ".\n");

    }
}