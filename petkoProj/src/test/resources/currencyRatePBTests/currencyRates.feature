@CurrencyExchange @FullRegression
Feature: Currency Exchange Tests


  @R006
  Scenario Outline: R006 Comparison of the UI and API rates
    Given User opens 'Main' page and accept cookies
    And User gets currency rates by API
    When User checks currency rates on the UI
    Then User compares rate for '<currency>'

    Examples:
      | currency |
      | USD      |
      | EUR      |
