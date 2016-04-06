package com.fox.automation.framework.pages;

import com.fox.automation.framework.core.FoxDriver;

public abstract class BasePage {
    protected FoxDriver driver;

    public BasePage(FoxDriver driver){
        this.driver = driver;
    }

    public void open(){
    }

    public abstract void verify();
}
