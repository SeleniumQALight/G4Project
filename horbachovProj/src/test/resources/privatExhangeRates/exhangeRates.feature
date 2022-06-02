Feature: Exchange Rates Feature

  @P001

  Scenario Outline: P001 compare api and ui rates
    Given User sends request to Privat Bank API for '<ccy>'
    When  User opens pb.ua
    And User checks '<ccy>' rate on ui
    Then Api and ui rates are equal


    Examples:
      | ccy |
      | USD |
      | EUR |



