
  Feature: Exchange rates tests

  Given Open main page

    @P001
    Scenario :
      When Take take exchange rates from home page and set them into 'currency_buy' and 'currency_sale'
      And Take exchange rates from API request for exchange rates
      Then Compare buy and sale exchange rates rates
