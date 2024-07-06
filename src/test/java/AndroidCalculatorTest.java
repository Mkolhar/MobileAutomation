import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.example.appium.utils.CalculatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidCalculatorTest {
    CalculatorUtils calculatorUtils;
    AndroidDriver driver;

    public static void main(String[] args) {
        try {
            new AndroidCalculatorTest().openCalculator();

        } catch (Exception e) {
            System.out.println("Woe ---->" +e.getCause());
            System.out.println("Woe ---->" +e);
            System.out.println("Woe ---->" +e.getMessage());
        }
    }

    @BeforeClass
    public void openCalculator() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Use the "appium:" prefix for non-W3C standard capabilities
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 8 Pro");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5556");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15");
        capabilities.setCapability("appPackage", "com.google.android.calculator");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);


        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);

        calculatorUtils = new CalculatorUtils(driver);

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Add some basic actions to verify the calculator opens correctly
//        driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
//        driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
//        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
//        driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
//        String result = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();

//        System.out.println("Calculation result: " + result);


//        System.out.println("Calculation result: " );

//        driver.quit();
    }
    @Test
    public void testAddition() {
        calculatorUtils.performOperation(9, "add", 5, "14");
    }

    @Test
    public void testSubtraction() {
        calculatorUtils.performOperation(8, "subtract", 3, "5");
    }

    @Test
    public void testMultiplication() {
        calculatorUtils.performOperation(14, "multiply", 5, "70");
    }

    @Test
    public void testDivision() {
        calculatorUtils.performOperation(70, "divide", 10, "7");
    }

    @Test
    public void testComplexSeries() {
        calculatorUtils.performComplexOperation("100+200-100*2/5", "140");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
