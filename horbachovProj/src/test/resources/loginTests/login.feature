@LoginTest @FullRegression
Feature: User Login

  @R001
  Scenario Outline: R001 Login with invalid Login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'

    Examples:
      | login       | password |
      | Wrong login | 1234     |

  @R002
  Scenario Outline: R002 Login with valid Login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees 'Sign Out' button

    Examples:
      | login       | password |
      | serge | qwerty123456     |


  @R003
  Scenario Outline: R003 Registration with existing username
    Given User opens 'Login' page
    When User enters '<username>' into 'Username' input on 'Login' page
    And User enters '<email>' into 'Email' input on 'Login' page
    And User enters '<password>' into 'Password' input on 'Login' page
    And User click on 'Sign Up for OurApp' button on 'Login' page
    Then User sees 'That username is already taken.' error message

    Examples:
      | username       | email | password |
      | serge | testemail@proton.com    | 123eqwwewrewrewrw |

  @R004
  Scenario Outline: R004 Registration with invalid email
    Given User opens 'Login' page
    When User enters '<username>' into 'Username' input on 'Login' page
    And User enters '<email>' into 'Email' input on 'Login' page
    And User enters '<password>' into 'Password' input on 'Login' page
    And User click on 'Sign Up for OurApp' button on 'Login' page
    Then User sees 'You must provide a valid email address.' error message

    Examples:
      | username       | email | password |
      | serge1 | testemail   | 123eqwwewrewrewrw |


  @R005
  Scenario Outline: R005 Registration with invalid password
    Given User opens 'Login' page
    When User enters '<username>' into 'Username' input on 'Login' page
    And User enters '<email>' into 'Email' input on 'Login' page
    And User enters '<password>' into 'Password' input on 'Login' page
    And User click on 'Sign Up for OurApp' button on 'Login' page
    Then User sees 'Password must be at least 12 characters.' error message

    Examples:
      | username       | email | password |
      | serge1 | testemail@boom.com   | 123 |