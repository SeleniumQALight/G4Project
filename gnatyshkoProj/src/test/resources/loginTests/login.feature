 @LoginTest @FullRegression
 Feature: User Login

  @R001
  Scenario Outline: R001 Login with invalid login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / pasword'

    Examples:
      | login       | passWord |
      | Wrong login | 1234     |

  @R002
  Scenario Outline: R002 Login with valid login
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User opens 'Home' page and button 'signOut' is displayed

    Examples:
      | login  | passWord    |
      | iryna  | 02061989Ira%|

  @R003
  Scenario Outline: R003 Registrate with invalid credentials '<username>' '<email>' '<passWord>' '<text>'
    Given User opens 'Login' page
    When User enters '<username>' username into input 'Username' signUp
    And User enters '<email>' email into input 'Email' signUp
    And User enters '<passWord>' passWord into input 'PassWord' signUp
    And User clicks on 'SignUp' button
    Then User sees alert message with '<text>'

    Examples:
      | username | email         | passWord     | text                                                                                                                     |
      | tr       | qqq           | 345          | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | tr       | test@qqqq.com | 123456qwerty | Username must be at least 3 characters.                                                                                  |
      | tr       | qqq           | 123456qwerty | Username must be at least 3 characters.;You must provide a valid email address.                                          |



