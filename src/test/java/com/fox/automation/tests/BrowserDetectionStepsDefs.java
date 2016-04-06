package com.fox.automation.tests;


import com.fox.automation.framework.PageFactory;
import com.fox.automation.framework.core.FoxDriver;
import com.fox.automation.framework.core.FoxDriverFactory;
import com.fox.automation.framework.helpers.ConfigHelpers;
import com.fox.automation.framework.pages.PlayerPage;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class BrowserDetectionStepsDefs {

    @Then("^The browser's name is detected properly$")
    public void the_browser_s_name_is_detected_properly() throws Throwable {
        PlayerPage playerPage = (PlayerPage) PageFactory.getPage("player");
        String actualBrowserName = playerPage.getDetectedBrowserName();
        String expectedBrowserName = FoxDriverFactory.getStaticFoxDriver().getBrowserName();
        Assert.assertTrue(actualBrowserName.equalsIgnoreCase(expectedBrowserName));
    }
}
