@RegistrationTest @FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Registration error messages
    Given User opens 'Login' page
    When User enters '<login>' into 'Login' input in registration form on 'Login' page
    And User enters '<email>' into 'Email' input in registration form on 'Login' page
    And User enters '<password>' into 'Password' input in registration form on 'Login' page
    And User click on the 'Sign Up' button
    Then User sees alert message for login 'Username must be at least 3 characters.'
    And User sees alert message for email 'You must provide a valid email address.'
    And User sees alert message for password 'Password must be at least 12 characters.'

    Examples:
      | login    | email                  | password     |
      |  t       | test123                | qwerty123    |
