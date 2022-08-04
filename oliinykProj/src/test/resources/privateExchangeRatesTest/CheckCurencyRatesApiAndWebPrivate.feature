
  Feature: Exchange rates tests

    @P001
    Scenario Outline: P001 exchange rates test
      Given Open main page
      And Take '<currency>' exchange rates from home page web
      And Take exchange rates from API request for exchange rates
      Then Compare '<currency>' buy and sale exchange rates rates

      Examples:
      |currency|currency|
      |USD     |EUR     |

