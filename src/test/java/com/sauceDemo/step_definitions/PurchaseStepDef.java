package com.sauceDemo.step_definitions;

import com.github.javafaker.Faker;
import com.sauceDemo.pages.CartPage;
import com.sauceDemo.pages.CheckoutPage;
import com.sauceDemo.pages.InventoryPage;
import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.utilities.BrowserUtils;
import com.sauceDemo.utilities.ConfigurationReader;
import com.sauceDemo.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PurchaseStepDef {

    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

   static List<String> orderedProducts = new ArrayList<>();

    @Given("User logged in to sauceDemo with a user")
    public void user_logged_in_to_sauceDemo_with_a_user() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    @When("User add all products to the cart")
    public void user_add_all_products_to_the_cart() {
        for (WebElement eachAddToCartButton : inventoryPage.allAddToCartButtons) {
            eachAddToCartButton.click();
            BrowserUtils.sleep(1);
        }
    }

    @And("User click the Cart link on the right corner")
    public void user_click_the_cart_link_on_the_right_corner() {
        inventoryPage.cartLink.click();
        BrowserUtils.sleep(1);
    }

    @Then("User should be on Cart page")
    public void userShouldBeOnCartPage() {
        BrowserUtils.verifyURLContains("cart");
        BrowserUtils.sleep(2);
    }

    @When("User delete all non-clothing related options from the cart")
    public void user_delete_all_non_clothing_related_options_from_the_cart() {

        List<WebElement> tempProductList = new ArrayList<>(cartPage.listOfProducts);
        List<WebElement> tempRemoveButtonsList = new ArrayList<>(cartPage.allRemoveButtons);

        for (int i = 0; i < tempProductList.size(); i++) {
            WebElement cartItem = tempProductList.get(i);
            WebElement removeButton = tempRemoveButtonsList.get(i);

            // Check if the cart item is a clothing product or not based on some criteria
            if (!cartPage.isClothingProduct(cartItem)) {
                // Click the remove button to delete the non-clothing product
                removeButton.click();
                BrowserUtils.sleep(2);
            }
        }
        BrowserUtils.sleep(3);
    }

    @And("User proceed to the checkout page")
    public void user_proceed_to_the_checkout_page() {
        cartPage.checkoutButton.click();
        BrowserUtils.sleep(1);
    }

    @Then("User should be on Checkout page")
    public void userShouldBeOnCheckoutPage() {
        BrowserUtils.verifyURLContains("checkout");
    }

    @When("User enter the required user information")
    public void user_enter_the_required_user_information() {
        Faker faker = new Faker();
        checkoutPage.firstNameInputBox.sendKeys(faker.name().firstName());
        BrowserUtils.sleep(1);
        checkoutPage.lastNameInputBox.sendKeys(faker.name().firstName());
        BrowserUtils.sleep(1);
        checkoutPage.postalCodeInputBox.sendKeys(faker.address().zipCode());
        BrowserUtils.sleep(1);
    }

    @And("User click the Continue button")
    public void userClickTheContinueButton() {
        checkoutPage.continueButton.click();
        BrowserUtils.sleep(1);
    }

    @And("User finalize the payment")
    public void user_finalize_the_payment() {
        checkoutPage.finishButton.click();
        BrowserUtils.sleep(1);
    }

    @And("User go back to the homepage")
    public void user_go_back_to_the_homepage() {
        checkoutPage.backHomeButton.click();
        BrowserUtils.sleep(1);
    }

    @Then("User should check the homepage")
    public void user_should_check_the_homepage() {
        BrowserUtils.verifyURLContains("inventory");

        //in order to make new list I declared new ArrayList for following scenario
        orderedProducts = new ArrayList<>();
    }


    @When("User sort the products by price")
    public void user_sort_the_products_by_price() {
        Select select = new Select(inventoryPage.sortProduct);
        select.selectByVisibleText("Price (low to high)");
        BrowserUtils.sleep(2);
    }

    @And("User add random {int} products to the cart")
    public void user_add_random_products_to_the_cart(int randomNumberOfProducts) {
        Random random = new Random();
        //in order to keep constant size of add to cart buttons I've created following list
        List<WebElement> tempAddToCartButton =  inventoryPage.allAddToCartButtons;
        for (int i = 0; i < randomNumberOfProducts; i++) {
            int numberOfButtons = inventoryPage.allAddToCartButtons.size();
            // Generate a random index to click on a random "Add to Cart" button
            int randomIndex = random.nextInt(numberOfButtons);
            System.out.println("randomIndex = " + randomIndex);
            tempAddToCartButton.get(randomIndex).click();
            BrowserUtils.sleep(2);
        }
        BrowserUtils.sleep(2);
    }

    @And("User should see {int} products in the cart")
    public void userShouldSeeProductsInTheCart(int randomNumberOfProducts) {
        Assert.assertEquals(randomNumberOfProducts, cartPage.listOfProducts.size());
        for (int i = 0; i < cartPage.listOfProducts.size(); i++) {
            orderedProducts.add(cartPage.listOfProducts.get(i).getText());
        }
        BrowserUtils.sleep(2);

        for (int i = 0; i < orderedProducts.size(); i++) {
            System.out.println("orderedProducts.get(i) = " + orderedProducts.get(i));
        }
    }


    @Then("User should see the summary of the order to ensure accuracy")
    public void userShouldSeeTheSummaryOfTheOrderToEnsureAccuracy() {
        double expectedTotalPrice = 0;

        for (int i = 0; i < checkoutPage.listOfPrices.size(); i++) {
            String price = checkoutPage.listOfPrices.get(i).getText();
            price = price.substring(1);
            double doubleValue = Double.parseDouble(price);
            expectedTotalPrice += doubleValue;
        }

        System.out.println("expectedTotalPrice = " + expectedTotalPrice);

        String itemTotalPrice = checkoutPage.itemTotalPrice.getText();
        itemTotalPrice = itemTotalPrice.substring(itemTotalPrice.indexOf("$") + 1);
        double actualTotalPrice = Double.parseDouble(itemTotalPrice);
        double delta = 0.01;  //Adjust the delta value according to your precision requirements
        System.out.println("actualTotalPrice = " + actualTotalPrice);

        //price calculation assertion
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice, delta);

        // product name assertion
        System.out.println("orderedProducts.size() = " + orderedProducts.size());
        System.out.println("checkoutPage.listOfProducts.size() = " + checkoutPage.listOfProducts.size());


        for (int i = 0; i < checkoutPage.listOfProducts.size(); i++) {
            System.out.println("checkoutPage.listOfProducts.get(i) = " + checkoutPage.listOfProducts.get(i).getText());
        }

        for (int i = 0; i < orderedProducts.size(); i++) {
            Assert.assertEquals(orderedProducts.get(i), checkoutPage.listOfProducts.get(i).getText());
        }




        ////div[@class='inventory_item_name']/../../..//div[@class='inventory_item_price']

    }
}



