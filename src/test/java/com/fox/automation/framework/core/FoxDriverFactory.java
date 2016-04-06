package com.fox.automation.framework.core;

import com.fox.automation.framework.helpers.ConfigHelpers;
import org.openqa.selenium.WebDriverException;

import java.io.*;
import java.net.MalformedURLException;

public class FoxDriverFactory {
    private static FoxDriver staticChromeDriver;
    private static FoxDriver staticIEDriver;
    private static FoxDriver staticFirefoxDriver;
    private static FoxDriver staticRemoteDriver;

    public static FoxDriver getStaticFoxDriver() throws IOException {
        String browserName = getBrowserName();
        if(browserName == null){
            return null;
        }

        if(browserName.equalsIgnoreCase("FIREFOX")){
            staticFirefoxDriver = getExistingOrCreateNewDriver(staticFirefoxDriver, browserName);
            return staticFirefoxDriver;

        } else if(browserName.equalsIgnoreCase("CHROME")){
            staticChromeDriver = getExistingOrCreateNewDriver(staticChromeDriver, browserName);
            return staticChromeDriver;

        } else if(browserName.equalsIgnoreCase("IE")){
            staticIEDriver = getExistingOrCreateNewDriver(staticIEDriver, browserName);
            return staticIEDriver;
        } else if(browserName.equalsIgnoreCase("REMOTE")){
            staticRemoteDriver = getExistingOrCreateNewDriver(staticRemoteDriver, browserName);
            return staticRemoteDriver;
        }

        return null;
    }

    private static FoxDriver getExistingOrCreateNewDriver(FoxDriver driver, String browserName) throws MalformedURLException {
        if(isDriverResponsive(driver)){
            return driver;
        }
        return new FoxDriver(browserName);
    }

    private static Boolean isDriverResponsive(FoxDriver driver){
        if(driver == null){
            return false;
        }
        try{
            driver.getWebDriver().getTitle();
            return true;
        }
        catch (WebDriverException e){
            return false;
        }
    }

    private static String getBrowserName() throws IOException {
        return ConfigHelpers.getBrowserName();
    }
}
