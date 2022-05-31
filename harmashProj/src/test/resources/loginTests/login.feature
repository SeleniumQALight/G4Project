@LoginTest @FullRegression
  Feature: User Login

    @R001
    Scenario Outline: R001 Login with invalid Login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees alert message with text 'Invalid username / pasword'

      Examples:
      | login       | passWord |
      | Wrong login | 12345    |

    @R020
    Scenario Outline: R020 Login with valid Login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<passWord>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees avatar

      Examples:
        | login |passWord|
        | fai   |fai123456789|