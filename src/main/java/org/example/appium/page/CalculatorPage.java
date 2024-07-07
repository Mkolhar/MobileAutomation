package org.example.appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorPage {
    private AndroidDriver driver;

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "com.google.android.calculator:id/op_add")
    public WebElement add;

    @FindBy(id = "com.google.android.calculator:id/op_sub")
    public WebElement sub;

    @FindBy(id = "com.google.android.calculator:id/op_mul")
    public WebElement mul;

    @FindBy(id = "com.google.android.calculator:id/op_div")
    public WebElement div;

    @FindBy(id = "com.google.android.calculator:id/eq")
    public WebElement equals;

    @FindBy(id = "com.google.android.calculator:id/result_final")
    public WebElement finalResult;

    public WebElement getDigit(char digit) {
        String digitId = "digit_" + digit;
        return driver.findElement(By.id("com.google.android.calculator:id/" + digitId));
    }
}
