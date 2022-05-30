@CurrencyExchange @FullRegression
Feature: Currency Exchange Tests


  @R006
  Scenario Outline: R006 Comparison of the UI and API rates
    Given User opens 'Main' page and accept cookies
    And User gets currency rates by API for '<currency>'
    When User checks currency rates on the UI for '<currency>'
    Then User compares rates from UI and API

    Examples:
      | currency |
      | USD      |
      | EUR      |
