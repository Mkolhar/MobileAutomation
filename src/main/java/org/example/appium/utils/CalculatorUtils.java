package org.example.appium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.appium.page.CalculatorPage;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import org.testng.AssertJUnit;
import org.testng.Assert;


public class CalculatorUtils {

    private static final Logger logger = LogManager.getLogger(CalculatorUtils.class);
    private AndroidDriver driver;
    CalculatorPage calculatorPage;
    public CalculatorUtils(AndroidDriver driver) {
        this.driver = driver;
        calculatorPage = new CalculatorPage(driver);
    }


    public void clickDigit(int digit) {
        String digitStr = String.valueOf(digit);
        // To Handle values greater than 9 ( more than 1 digit values)
        for (char d : digitStr.toCharArray()) {
            calculatorPage.getDigit(d).click();
        }
    }

    public void clickOperation(String operation) {
        switch (operation) {
            case "add":
                calculatorPage.add.click();
                break;
            case "subtract":
                calculatorPage.sub.click();
                break;
            case "multiply":
                calculatorPage.mul.click();
                break;
            case "divide":
                calculatorPage.div.click();
                break;
            case "equals":
                calculatorPage.equals.click();
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

    public String getResult() {
        return calculatorPage.finalResult.getText();
    }

    public String performOperation(int num1, String operation, int num2, String expectedResult) {
        clickDigit(num1);
        clickOperation(operation);
        clickDigit(num2);
        clickOperation("equals");
        String result = getResult();
//        logger.info(operation.substring(0, 1).toUpperCase() + operation.substring(1) + " Result: " + result);
        Assert.assertEquals(result, expectedResult);

        logger.info("On performing "+num1+ " "+ operation+" "+ num2 +" we have obtained "+ result + " Expected value is "+ expectedResult);
        return result;
    }

    public String performComplexOperation(String mathOperation, String expectedResult) {
        String[] components = mathOperation.split("(?<=[-+*/])|(?=[-+*/])");

        for (String component : components) {
            switch (component.trim()) {
                case "+":
                    clickOperation("add");
                    break;
                case "-":
                    clickOperation("subtract");
                    break;
                case "*":
                    clickOperation("multiply");
                    break;
                case "/":
                    clickOperation("divide");
                    break;
                default:
                    int number = Integer.parseInt(component.trim());
                    clickDigit(number);
                    break;
            }
        }

        clickOperation("equals");
        String result = getResult();
        logger.info("On performing " + mathOperation + ", obtained result: " + result + ". Expected result: " + expectedResult);
        Assert.assertEquals(result, expectedResult);

        return result;
    }

}
