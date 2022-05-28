  @LoginTest @FullRegression
  Feature: User login

    @R001
    Scenario Outline: R001 Login with invalid login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees alert message with text 'Invalid username / pasword'

      Examples:
      | login       | password |
      | Wrong login | pram     |

    @R002
    Scenario Outline: R002 Login with valid login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees 'Sign Out' button

      Examples:
      | login    | password     |
      | aoliinyk | qwerty123456 |

    @R003
    Scenario Outline: R003 Registration error messages
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input in registration form on 'Login' page
      Then User sees pop-out alert message for login 'Username must be at least 3 characters.'
      When User enters '<email>' (not valid) email into 'Email' input in registration form on 'Login' page
      Then User sees another alert message for email 'You must provide a valid email address.'
      When User enters '<password>' password into 'Password' input in registration form on 'Login' page
      Then User sees even other alert message for password 'Password must be at least 12 characters.'
      When User enters '<login>' login into 'Login' input in registration form on 'Login' page
      Then User could see other alert message that pop-ups 'That username is already taken.'

      Examples:
      | login    | email                  | password |
      |  a       | test                   | qwert    |
      | aoliinyk | livecounter86@gmail.com|          |