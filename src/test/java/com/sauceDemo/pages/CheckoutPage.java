package com.sauceDemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    public WebElement firstNameInputBox;
    @FindBy(id = "last-name")
    public WebElement lastNameInputBox;
    @FindBy(id = "postal-code")
    public WebElement postalCodeInputBox;
    @FindBy(id = "continue")
    public WebElement continueButton;
    @FindBy(id = "finish")
    public WebElement finishButton;
    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> listOfPrices ;
    @FindBy(className = "summary_subtotal_label")
    public WebElement itemTotalPrice;

    @FindBy(css = "[class='inventory_item_name']")
    public List<WebElement> listOfProducts;


}
