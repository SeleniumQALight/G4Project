Feature: Exchange in Privat Feature

  @R0010

  Scenario Outline: R0010 get API rates and compare with UI
    Given User sends request  anfd get rate '<ccy>'
    When  User opens private site
    And User checks '<ccy>' rate on UI
    Then API and UI rates are equal


    Examples:
      | ccy |
      | USD |
      | EUR |
