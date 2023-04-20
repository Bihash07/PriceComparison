package samples;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import org.testng.annotations.*;

public class AppiumTest {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        // Desired capabilities for Android device
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.example.app");
        capabilities.setCapability("appActivity", "MainActivity");

        // Appium server URL
        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        // Initialize driver
        driver = new AndroidDriver(url, capabilities);
    }

    @DataProvider(name = "inputData")
    public Object[][] getData() {
        return new Object[][] {
                {"input1", "Expected result for input 1"},
                {"input2", "Expected result for input 2"},
                {"input3", "Expected result for input 3"}
        };
    }

    @Test(dataProvider = "inputData")
    public void test(String input, String expectedOutput) {
        WebElement inputField = driver.findElement(By.id("inputField"));
        inputField.sendKeys(input);

        WebElement submitButton = driver.findElement(By.id("submitButton"));
        submitButton.click();

        // Assert result
        WebElement resultElement = driver.findElement(By.id("resultElement"));
        String resultText = resultElement.getText();
        assert resultText.equals(expectedOutput);

        // Clear input field for next test
        inputField.clear();
    }

    @AfterClass
    public void tearDown() {
        // Close driver
        driver.quit();
    }
}


