package com.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.appium.utils.CalculatorUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;

public class AndroidCalculatorTest {
    CalculatorUtils calculatorUtils;
    AndroidDriver driver;
    private static final Logger logger = LogManager.getLogger(CalculatorUtils.class);

    @BeforeMethod
    public void logTestMethodName(Method method) {
        logger.info("Test Case Name: " + method.getName());
    }

    @BeforeClass
    public void openCalculator() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 8 Pro");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15");
        capabilities.setCapability("appPackage", "com.google.android.calculator");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);


        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);

        calculatorUtils = new CalculatorUtils(driver);
    }

    @Test
    public void additionOfNineAndFive() {
        try {
            calculatorUtils.performOperation(9, "add", 5, "14");
        } catch (Exception e) {
            logger.debug(e.toString());
            throw new RuntimeException(e);

        }
    }

    @Test
    public void subtractionOfEightAndThree() {
        try {
            calculatorUtils.performOperation(8, "subtract", 3, "5");
        } catch (Exception e) {
            logger.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void multiplicationOfFourteenAndFive() {
        try {
            calculatorUtils.performOperation(14, "multiply", 5, "70");
        } catch (Exception e) {
            logger.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void divisionOfSeventyAndTen() {
        try {
            calculatorUtils.performOperation(70, "divide", 10, "7");
        } catch (Exception e) {
            logger.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCaseWithMultipleOperations() {
        try {
            //This Works On the bases of BODMASS rule
            calculatorUtils.performComplexOperation("100+200-100*2/5", "260");
        } catch (Exception e) {
            logger.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void setLoggerBreak() {
        // This will provide the proper line brake in log
        logger.info("********************************************************************************** \n ");
    }

    @AfterClass
    public void closeCalculatorApp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
