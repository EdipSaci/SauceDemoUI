package com.sauceDemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[.='Remove']")
    public List<WebElement> allRemoveButtons;

    @FindBy(css = "[class='inventory_item_name']")
    public List<WebElement> listOfProducts;

    public boolean isClothingProduct(WebElement cartItem) {
        String cartItemText = cartItem.getText().toLowerCase(); // Convert to lowercase for case-insensitive comparison

        // Check if the cart item text contains any of the clothing keywords
        return cartItemText.contains("t-shirt") || cartItemText.contains("jacket") || cartItemText.contains("onesie");
    }

    @FindBy(id = "checkout")
    public WebElement checkoutButton;


}
