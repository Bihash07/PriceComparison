package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;

public class AppRunner {

    public static AndroidDriver pickmeDriver, uberDriver;

    @BeforeTest
    public static void openApp() throws Exception {

        AppiumServer1.startServer();
        AppiumServer2.startServer();

        // Set DesiredCapabilities for Pickme app to send to the appium Server
        DesiredCapabilities pickmeCap = new DesiredCapabilities();
        pickmeCap.setCapability("platformName", Assets.PICKME_PLATFORM_NAME);
        pickmeCap.setCapability("deviceName", Assets.PICKME_DEVICE_NAME);
        pickmeCap.setCapability("udid", Assets.PICKME_UDID);
        pickmeCap.setCapability("platformVersion", Assets.PICKME_PLATFORM_VERSION);
        pickmeCap.setCapability("appPackage", Assets.PICKME_APP_PACKAGE);
        pickmeCap.setCapability("appActivity", Assets.PICKME_APP_ACTIVITY );
        pickmeCap.setCapability("noReset", true);
        pickmeCap.setCapability("ensureWebviewsHavePages", true);
        pickmeCap.setCapability("nativeWebScreenshot", true);
        pickmeCap.setCapability("connectHardwareKeyboard", true);

        // Set DesiredCapabilities for Uber app to send to the appium Server
        DesiredCapabilities uberCap = new DesiredCapabilities();
        uberCap.setCapability("platformName", Assets.UBER_PLATFORM_NAME);
        uberCap.setCapability("deviceName",Assets.UBER_DEVICE_NAME);
        uberCap.setCapability("udid", Assets.UBER_UDID );
        uberCap.setCapability("platformVersion", Assets.UBER_PLATFORM_VERSION);
        uberCap.setCapability("appPackage", Assets.UBER_APP_PACKAGE);
        uberCap.setCapability("appActivity", Assets.UBER_APP_ACTIVITY);
        uberCap.setCapability("noReset", true);
        uberCap.setCapability("ensureWebviewsHavePages", true);
        uberCap.setCapability("nativeWebScreenshot", true);
        uberCap.setCapability("connectHardwareKeyboard", true);


        // Create AppiumDriver instances for each device
        // Initialize driver 2 for Uber App
        pickmeDriver = new AndroidDriver(new URL("http://localhost:" +  Assets.appiumPort1 + "/wd/hub"), pickmeCap);
        System.out.println("Pickme App was launched.");

        uberDriver = new AndroidDriver(new URL("http://localhost:" +  Assets.appiumPort2 + "/wd/hub"), uberCap);
        System.out.println("Uber App was launched.");

        Thread.sleep(5000);
    }

    public static void closeApps() {
        pickmeDriver.quit();
        uberDriver.quit();
    }

    @AfterTest
    public static void tearDown() {
        // Quit both AppiumDriver instances
        AppiumServer1.stopServer();
        AppiumServer2.stopServer();
    }

}
