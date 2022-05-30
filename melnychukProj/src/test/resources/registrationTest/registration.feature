@RegistrationTest
@FullRegression
Feature: User Registration

  @R003
  Scenario Outline: R003 Register with invalid name
    Given User opens 'Login' page
    When User enters   '<Username>' into 'Register name' input on 'Login' page
    And User enters '<Password>' into 'Register password' input on 'Login' page
    And User enters '<Email>' into 'Register Email' input on 'Login' page
    And User click on 'SingUp' button on 'Login' page
    Then User sees alert message  for the wrong username with text 'Username must be at least 3 characters.'

    Examples:
      | Username | Password     | Email          |
      | o        | Qwerty123456 | test@gmail.com |

  @R005
  Scenario Outline: R003 Register with invalid password
    Given User opens 'Login' page
    When User enters   '<Username>' into 'Register name' input on 'Login' page
    And User enters '<Password>' into 'Register password' input on 'Login' page
    And User enters '<Email>' into 'Register Email' input on 'Login' page
    And User click on 'SingUp' button on 'Login' page
    Then User sees alert message  for the wrong password with text 'Password must be at least 12 characters.'

    Examples:
      | Username | Password | Email          |
      | olga     | q        | test@gmail.com |

  @R006
  Scenario Outline: R003 Register with invalid email
    Given User opens 'Login' page
    When User enters   '<Username>' into 'Register name' input on 'Login' page
    And User enters '<Password>' into 'Register password' input on 'Login' page
    And User enters '<Email>' into 'Register Email' input on 'Login' page
    And User click on 'SingUp' button on 'Login' page
    Then User sees alert message  for the wrong email with text 'You must provide a valid email address.'

    Examples:
      | Username | Password       | Email     |
      | olga     | qsfgsdjkhds212 | gmail.com |

  @R007
  Scenario Outline: R007  error messages
    Given User opens 'Login' page
    When User enters   '<Username>' into 'Register name' input on 'Login' page
    And User enters '<Password>' into 'Register password' input on 'Login' page
    And User enters '<Email>' into 'Register Email' input on 'Login' page
    And User click on 'SingUp' button on 'Login' page
    Then User sees alert message '<Text>'
    Examples:
      | Username | Password       | Email          | Text                                     |
      | o        | Qwerty123456   | test@gmail.com | Username must be at least 3 characters.  |
      | olga     | q              | test@gmail.com | Password must be at least 12 characters. |
      | olga     | qsfgsdjkhds212 | gmail.com      | You must provide a valid email address.  |

