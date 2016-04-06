package com.fox.automation.framework;

import com.fox.automation.framework.core.FoxDriverFactory;
import com.fox.automation.framework.helpers.ConfigHelpers;
import com.fox.automation.framework.pages.*;
import java.io.IOException;

public class PageFactory {
    public static BasePage getPage(String pageName) throws IOException {
        if (pageName == null) {
            return null;
        }
        else if  (pageName.equalsIgnoreCase("home")) {
            return new HomePage(FoxDriverFactory.getStaticFoxDriver(), ConfigHelpers.getBaseUrl());
        }
        else if  (pageName.equalsIgnoreCase("player")) {
            return new PlayerPage(FoxDriverFactory.getStaticFoxDriver(), ConfigHelpers.getBaseUrl());
        }

        return null;
    }
}
