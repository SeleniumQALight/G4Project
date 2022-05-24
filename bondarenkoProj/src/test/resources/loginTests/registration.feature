@RegistrationTest @FullRegression
Feature: User Registration

  @R001
  Scenario Outline: R001 Check errors messages in the registration form
    Given  User opens 'Login' page
    When User enters '<username>' username into 'UserName' input on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Login' page
    And User click on 'SignUpForOurApp' button on 'Login' page
    Then User sees '<error(s) message(s)>' in the registration form

    Examples:
      | username| email         | password     | error(s) message(s)                                                                                                      |
      | w       | 1234          | yt           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tr      | test@qqqq.com | 123456qwerty | Username must be at least 3 characters.                                                                                  |
      | tr      | test          | 123456qwerty | Username must be at least 3 characters.;You must provide a valid email address.                                          |


