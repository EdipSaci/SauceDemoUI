Feature: login functionality

  Acceptance Criterias:
  1. User should be able to login to with valid credentials
  2. User should not be able to login with valid username and invalid password
  3. User should not be able to login with valid password and invalid username

  4. User should not be able to login with empty username and empty password
  5. User should not be able to login with empty username and valid password
  6. User should not be able to login with empty username and invalid password

  7. User should not be able to login with empty password and valid username
  8. User should not be able to login with empty password and invalid username

  Background: User is already on the login page
    Given User is on the login page

  Scenario Outline: User should be able to login to with VALID credentials
    When the user enters "<username>" "<password>" and click login button
    Then they should be logged in successfully and redirected to the inventory

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
      | problem_user  | secret_sauce |

  Scenario Outline: User should not be able to login with INVALID credentials
    When the user enters "<username>" "<password>" and click login button
    Then they should not be logged in successfully
    And they should see "<error message>"

    Examples:
      | username      | password        | error message                                                             |
      | standard_user | invalidPassword | Epic sadface: Username and password do not match any user in this service |
      | invalidName   | secret_sauce    | Epic sadface: Username and password do not match any user in this service |
      |               |                 | Epic sadface: Username is required                                        |
      |               | secret_sauce    | Epic sadface: Username is required                                        |
      |               | invalidPassword | Epic sadface: Username is required                                        |
      | standard_user |                 | Epic sadface: Password is required                                        |
      | invalidName   |                 | Epic sadface: Password is required                                        |

