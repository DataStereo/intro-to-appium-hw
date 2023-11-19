package com.example.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageView {
    AppiumDriver driver;

    @AndroidFindBy(id = "edit")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'IntegerA'")
    private WebElement textField;

    public PageView(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTextField() {
        return textField.getText();
    }

    public PageView setTextField(String text) {
        textField.sendKeys(text);
        return this;
    }
}
