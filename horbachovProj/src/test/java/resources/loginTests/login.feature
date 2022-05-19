@LoginTest @FullRegression
Feature: User Login

  @R001
  Scenario Outline: R001 Login with invalid Login
    Given User opens 'Login' page

    Examples:
      | login       | password |
      | Wrong login | 1234     |