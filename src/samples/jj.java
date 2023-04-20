package samples;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static utils.AppRunner.pickmeDriver;
import static utils.AppRunner.uberDriver;

public class jj {

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
            pickmeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    WebElement pickmeTukPriceTxt6 = pickmeDriver.findElement(ElementLocator.PICKME_PRICE);
    String pickmeTukPrice6 = pickmeTukPriceTxt6.getText();


    LocalDateTime now6 = LocalDateTime.now();
    String Time6 = now6.format(Time);

            System.out.println(Assets.Blue +
            "PriceList >> \n"+
            " Uber Tuk price: " + Assets.Yellow + uberTukPrice6 + Assets.Blue + ".\n " +
//                    " Uber Tuk With outDisprice: " + Assets.Yellow + uberTukWithoutDis6 + Assets.Blue + ".\n " +
            "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_06 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_06 + Assets.Blue + ".\n " +
            "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
            "Time: " + Assets.Yellow + Time6 + ". \n" + Assets.Blue + "\n" +
            " Pickme Tuk price: " + Assets.Yellow + pickmeTukPrice6 + Assets.Blue + ".\n " +
//                    " Pickme Tuk With outDisprice: " + Assets.Yellow + pickmeTukWithoutDis6 + Assets.Blue + ".\n " +
            "Location: " + Assets.Yellow + Locations.PICKUP_LOCATION_06 + Assets.Blue + "  to " + Assets.Yellow + Locations.DROP_LOCATION_06 + Assets.Blue + ".\n " +
            "Date: " + Assets.Yellow + Assets.date + Assets.Blue + ".\n " +
            "Time: " + Assets.Yellow + Time6 + ".\n");

            uberDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            pickmeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON1).click();
            AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON).click();
            AppRunner.uberDriver.findElement(ElementLocator.UBER_BACK_BUTTON2).click();
            AppRunner.pickmeDriver.findElement(ElementLocator.PICKME_BACK_BUTTON2).click();

//            Function.backBtn();
    }
}
