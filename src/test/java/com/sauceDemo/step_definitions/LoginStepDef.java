package com.sauceDemo.step_definitions;

import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.utilities.BrowserUtils;
import com.sauceDemo.utilities.ConfigurationReader;
import com.sauceDemo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
    @When("the user enters {string} {string} and click login button")
    public void the_user_enters_and_click_login_button(String username, String password) {
        loginPage.login(username, password);
    }
    @Then("they should be logged in successfully and redirected to the inventory")
    public void they_should_be_logged_in_successfully_and_redirected_to_the_inventory() {
        BrowserUtils.verifyURLContains("inventory");
    }

    @Then("they should not be logged in successfully")
    public void they_should_not_be_logged_in_successfully() {
        BrowserUtils.verifyElementDisplayed(loginPage.errorMessage);
    }
    @Then("they should see {string}")
    public void they_should_see(String errorMessage) {
       if(loginPage.userNameInputBox.getAttribute("value").isEmpty()) {
           Assert.assertTrue(loginPage.errorMessage.getText().contains("Epic sadface: Username is required"));
       }else if (loginPage.passwordInputBox.getAttribute("value").isEmpty()) {
           Assert.assertTrue(loginPage.errorMessage.getText().contains("Epic sadface: Password is required"));
       } else {
            Assert.assertTrue(loginPage.errorMessage.getText().contains("Epic sadface: Username and password do not match any user in this service"));
        }

    }

}
