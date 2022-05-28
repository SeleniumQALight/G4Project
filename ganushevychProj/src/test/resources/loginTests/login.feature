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
    Scenario: R002 Login with valid login
      Given User opens 'Login' page
      When User enters 'sasha' login into 'Login' input on 'Login' page
      And User enters 'Qwerty1234567' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees button 'SingOut' is displayed

    @R003
    Scenario Outline: R003 Registration with invalid data
      Given User opens 'Login' page
      When User enters '<login>' into 'Registration username' input on 'Login' page
      And User enters '<email>' into 'Registration email' input on 'Login' page
      And User enters '<password>' into 'Registration password' input on 'Login' page
      Then User sees error messages '<expectedErrors>' on 'Login' page

      Examples:
        | login       | email        |    password  |  expectedErrors                                                                                                           |
        |   tr        | qqq          |      345     | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.  |
        |   tr        |test@qqqq.com | 123456qwerty | Username must be at least 3 characters.                                                                                   |
        |   tr        |test          | 123456qwerty | Username must be at least 3 characters.;You must provide a valid email address.                                           |
