package com.fox.automation.framework.pages;

import com.fox.automation.framework.core.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    private String baseUrl;
    public HomePage(FoxDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void open(){
        driver.openUrl(baseUrl);
    }

    public void verify() throws NoSuchElementException {
        if(!(header().isDisplayed())) {
            throw new NoSuchElementException("Header is not present on the Home Page");
        }
    }

    public WebElement header(){
        return driver.getWebDriver().findElement(By.xpath("//h1[text()='Welcome to FOX']"));
    }
}
