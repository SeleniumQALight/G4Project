@privatBankExchangeRatesTest @FullRegression
Feature: ExchangeRates

  @R005
  Scenario Outline: R005 Check exchange rates
    Given  User opens 'PrivatBankHome' page
    When User gets '<currency>' exchange rate from UI
    And User gets '<currency>' exchange rate from API
    Then User sees the exchange rate is the same from UI and API

    Examples:
      | currency |
      | USD      |
      | EUR      |
