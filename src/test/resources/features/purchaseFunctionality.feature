@wip
Feature: Shopping on sauceDemo.com with all clothing products

  Background: User is already on the login page
    Given User logged in to sauceDemo with a user

  Scenario: Complete Shopping Process with all clothing products
    When User add all products to the cart
    And User click the Cart link on the right corner
    Then User should be on Cart page
    When User delete all non-clothing related options from the cart
    And User proceed to the checkout page
    Then User should be on Checkout page
    When User enter the required user information
    And User click the Continue button
    And User finalize the payment
    And User go back to the homepage
    Then User should check the homepage


  Scenario: Complete Shopping Process with Two random products
    When User sort the products by price
    And User add random 2 products to the cart
    And User click the Cart link on the right corner
    Then User should be on Cart page
    And User should see 2 products in the cart
    When User proceed to the checkout page
    Then User should be on Checkout page
    When User enter the required user information
    And User click the Continue button
    And User finalize the payment
    And User go back to the homepage
    Then User should check the homepage

  Scenario: Complete Shopping Process with Two random products and review order summary
    When User sort the products by price
    And User add random 2 products to the cart
    And User click the Cart link on the right corner
    Then User should be on Cart page
    And User should see 2 products in the cart
    When User proceed to the checkout page
    Then User should be on Checkout page
    When User enter the required user information
    And User click the Continue button
    Then User should see the summary of the order to ensure accuracy
    When User finalize the payment
    And User go back to the homepage
    Then User should check the homepage
