package com.sauceDemo.pages;

import com.sauceDemo.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

}
