  @LoginTest @FullRegression
  Feature: User Login

    @R001
    Scenario Outline: R001 Login with invalid login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees alert message with text 'Invalid username / pasword'

      Examples:
      | login       | password |
      | Wrong login | 1234     |

    @R002
    Scenario: R002 Login with valid credentials
      Given User opens 'Login' page
      When User enters 'ivantau777' login into 'Login' input on 'Login' page
      And User enters '123456789123' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees 'Sign Out' button


