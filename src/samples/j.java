package samples;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ElementLocator;

public class j {

    @DataProvider
    public static Object[][] inputData() {
        return new Object[][]{};
    }

    @Test(dataProvider = "inputData")
    public void myTest(String inputField, String inputValue) {
        // Perform your test steps using the inputField and inputValue parameters
        System.out.println("Running test for input field " + inputField + " with value " + inputValue);
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


}
