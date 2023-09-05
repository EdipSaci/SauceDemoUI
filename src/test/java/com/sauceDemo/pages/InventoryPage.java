package com.sauceDemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class InventoryPage extends BasePage{

    @FindBy(xpath = "//button[.='Add to cart']")
    public List<WebElement> allAddToCartButtons;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartLink;

    @FindBy(className = "product_sort_container")
    public WebElement sortProduct;


    @FindBy(css = "[class='inventory_item_name']")
    public List<WebElement> listOfProducts;
}
