  @LoginTest @FullRegression
  Feature: User login

    @R001
    Scenario Outline: R001 Login with invalid login
      Given User opens 'Login' page

      Examples:
      | login       | password |
      | Wrong login | pram     |