@LoginTest
@FullRegression
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

#ДЗ№2
# 1. Написати авто тест на Валіндний логін
# (ввести валідні значення і перевірити що залогінились - аватарка з'явилася)

 @R002
 Scenario Outline: R002 Login with valid Login
  Given User opens 'Login' page
  When User enters '<login>' login into 'Login' input on 'Login' page
  And User enters '<password>' passWord into 'PassWord' input on 'Login' page
  And User click on 'SingIn' button on 'Login' page
  Then User sees 'SignOut' button
  Examples:
   | login | password     |
   | olha  | Qwerty123456 |



