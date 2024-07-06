package org.example.appium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import org.testng.AssertJUnit;
import org.testng.Assert;


public class CalculatorUtils {
    private static final Logger logger = LogManager.getLogger(CalculatorUtils.class);
    private AndroidDriver driver;

    public CalculatorUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickDigit(int digit) {
        driver.findElement(By.id("com.google.android.calculator:id/digit_" + digit)).click();
    }

    public void clickOperation(String operation) {
        switch (operation) {
            case "add":
                driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
                break;
            case "subtract":
                driver.findElement(By.id("com.google.android.calculator:id/op_sub")).click();
                break;
            case "multiply":
                driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
                break;
            case "divide":
                driver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
                break;
            case "equals":
                driver.findElement(By.id("com.google.android.calculator:id/eq")).click();
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

    public String getResult() {
        return driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();
    }

    public void performOperation(int num1, String operation, int num2, String expectedResult) {
        clickDigit(num1);
        clickOperation(operation);
        clickDigit(num2);
        clickOperation("equals");
        String result = getResult();
        logger.info(operation.substring(0, 1).toUpperCase() + operation.substring(1) + " Result: " + result);
        Assert.assertEquals(result, expectedResult);
    }

    public void performComplexOperation(String series, String expectedResult) {
        for (char ch : series.toCharArray()) {
            switch (ch) {
                case '+':
                    clickOperation("add");
                    break;
                case '-':
                    clickOperation("subtract");
                    break;
                case '*':
                    clickOperation("multiply");
                    break;
                case '/':
                    clickOperation("divide");
                    break;
                default:
                    if (Character.isDigit(ch)) {
                        clickDigit(Character.getNumericValue(ch));
                    }
                    break;
            }
        }
        clickOperation("equals");
        String result = getResult();
        logger.info("Complex Series Result: " + result);
        Assert.assertEquals(result, expectedResult);
    }
}
