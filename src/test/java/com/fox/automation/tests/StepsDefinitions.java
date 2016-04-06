package com.fox.automation.tests;

import com.fox.automation.framework.PageFactory;
import com.fox.automation.framework.core.FoxDriver;
import com.fox.automation.framework.pages.BasePage;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;

public class StepsDefinitions {

    @Given("^I am on the \"?([^\"]*)\"? page$")
    public void i_am_on_the_page(String specific) throws Throwable {
        BasePage page = PageFactory.getPage(specific);
        page.open();
        page.verify();
    }

    @After()
    public void cleanUp(){
        FoxDriver.quitDriver();
    }

}
