package com.fox.automation.framework.pages;

import com.fox.automation.framework.core.FoxDriver;
import com.fox.automation.framework.helpers.ConfigHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PlayerPage extends BasePage {
    private String pathUrl = "player";
    private String baseUrl;

    public PlayerPage(FoxDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
    }

    public void open(){
        driver.openUrl(baseUrl + pathUrl);
    }

    public void verify() {
        if(!(button_play().isDisplayed())){
            throw new NoSuchElementException("The 'Play' button doesn't exist on the player page");
        }
    }

    public String getDetectedBrowserName(){
        return label_detectedBrowserName().getText();
    }

    //Controls
    private WebElement label_detectedBrowserName(){
        return driver.getWebDriver().findElement(By.xpath("//p[contains(text(),'You are using')]/b[1]"));
    }

    private WebElement button_play(){
        return driver.getWebDriver().findElement(By.xpath("//button[text()='Play']|//input[@value='Play' and contains(@onclick,'playUrl')]"));
    }
}
