package com.fox.automation.framework.core;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FoxDriver {
    //Time in Milliseconds
    private final int DEFAULT_TIMEOUT = 10000;
    private final int DEFAULT_TIME_BETWEEN_ATTEMPTS = 50;
    private final int DEFAULT_TIME_FIRST_BYTE_LIMIT = 1000;

    private static WebDriver webDriver;
    private JavascriptExecutor executor;

    public FoxDriver() throws MalformedURLException {
        this("firefox");
    }

    public FoxDriver(String browserName) throws MalformedURLException {
        initializeWebDriver(browserName);
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }

    public FoxDriver openUrl(String url){
        webDriver.navigate().to(url);
        waitForPageLoaded();
        return this;
    }

    public void setScreenSize(int width, int height){
        getWebDriver().manage().window().setSize(new Dimension(width, height));
    }

    public String getBrowserName(){
        Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
        String browserName = cap.getBrowserName();
        if(browserName.equalsIgnoreCase("internet explorer")){
            browserName = "ie";
        }
        return browserName;
    }

    private void initializeWebDriver(String browser) throws MalformedURLException {
        if(browser.equalsIgnoreCase("remote")){
            String hubUrl = "http://localhost:4444/wd/hub";

            String browserValue = System.getProperty("browserName");
            if(browserValue == null){
                browserValue = "firefox";
            }
            if (browserValue.equalsIgnoreCase("firefox")) {
                Capabilities caps = DesiredCapabilities.firefox();
                try {
                    webDriver = new RemoteWebDriver(new URL(hubUrl), caps);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Something wrong with hub url " + hubUrl);
                }
            } else if (browserValue.equalsIgnoreCase("chrome")){
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability("webdriver.chrome.driver","d:\\chromedriver.exe");
                try {
                    webDriver = new RemoteWebDriver(new URL(hubUrl), caps);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Something wrong with hub url " + hubUrl);
                }
            }
        }

        if(browser.equalsIgnoreCase("firefox")){
            webDriver = new FirefoxDriver();
        }
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driversExe/chromedriver.exe");
            webDriver = new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", "src/test/resources/driversExe/IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            webDriver = new InternetExplorerDriver(capabilities);
        }
        executor = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private void waitForPageLoaded(){
        waitForPageLoaded(DEFAULT_TIME_BETWEEN_ATTEMPTS, DEFAULT_TIMEOUT, DEFAULT_TIME_FIRST_BYTE_LIMIT);
    }

    private void waitForPageLoaded(int timeBetweenAttempts, int timeout, int timeFirstByteLimit){
        if(!isDocumentReadyStateComplete()){
            WaitUntilExpected(new Predicate<WebDriver>() {
                public boolean apply(WebDriver webDriver) {
                    return isDocumentReadyStateComplete();
                }
            }, timeout);
        }

        waitForPageSourceLoaded(timeBetweenAttempts, timeout, timeFirstByteLimit);
    }

    private boolean isDocumentReadyStateComplete() {
        return (Boolean)(executor).executeScript( "return (document.readyState == 'complete');" );
    }

    private void WaitUntilExpected(Predicate<WebDriver> predicate, int timeout){
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.until(predicate);
    }

    public static void quitDriver(){
        webDriver.quit();
    }

    private void waitForPageSourceLoaded(int timeBetweenAttempts, int timeout, int timeFirstByteLimit){
        //NotImplemented
    }
}
