
  Feature: Exchange rates tests

    @P001
    Scenario: P001 exchange rates test
      Given Open main page
      And Take take exchange rates from home page and set them into 'currency_buy' and 'currency_sale'
      And Take exchange rates from API request for exchange rates
      Then Compare buy and sale exchange rates rates
