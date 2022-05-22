@RegistrationTest @FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Registration error messages
    Given User opens 'Login' page
    When User enters '<login>' into 'Login' input in registration form on 'Login' page
    And User enters '<email>' into 'Email' input in registration form on 'Login' page
    And User enters '<password>' into 'Password' input in registration form on 'Login' page
    And User click on the 'Sign Up' button
    Then User sees alert message for login '<l_error>'
    And User sees alert message for email '<e_error>'
    And User sees alert message for password '<p_error>'

    Examples:
      |  login     | email                    | password     | l_error                                 | e_error                                 | p_error                                  |
      |  t         | 123                      | 123          | Username must be at least 3 characters. | You must provide a valid email address. | Password must be at least 12 characters. |
      |  t         | ivan.taurus777@gmail.com | 123          | Username must be at least 3 characters. | That email is already being used.       | Password must be at least 12 characters. |