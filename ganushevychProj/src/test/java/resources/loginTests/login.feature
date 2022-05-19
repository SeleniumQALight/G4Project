  @LoginTest @FullRegression
  Feature: User Login

    @R001
    Scenario Outline: R001 Login with invalid login
      Given User opens 'Login' page

      Examples:
      | Login       | password |
      | Wrong login | 1234     |