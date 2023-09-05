package com.sauceDemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    public WebElement userNameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

     @FindBy(id = "login-button")
     public WebElement loginButton;

     @FindBy(xpath = "//h3[@data-test='error']")
     public WebElement errorMessage;

     public void login(String username, String password){
         LoginPage loginPage= new LoginPage();
         loginPage.userNameInputBox.sendKeys(username);
         loginPage.passwordInputBox.sendKeys(password);
         loginPage.loginButton.click();
     }




}
