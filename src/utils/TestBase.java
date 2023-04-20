package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

public class TestBase {



    public static void swipeElement(WebElement element){
        TouchAction touchAction = new TouchAction(AppRunner.pickmeDriver);
        PointOption swipe = new PointOption();
        swipe.withCoordinates(-10, 0);
        touchAction.press(ElementOption.element(element)).waitAction().moveTo(swipe).release().perform();
    }
}
