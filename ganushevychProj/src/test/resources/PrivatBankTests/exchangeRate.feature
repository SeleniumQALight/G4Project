
  Feature: PrivatBank exchange rate

    @R005
    Scenario Outline: R005 PrivatBank exchange rate matching
      Given Get exchange rate via API for currency <Currency> and save needed courses
      When User opens PrivatBank 'Home' page
      And Get exchange rate for currency <Currency> on 'Home' page and save needed courses
      Then Check if exchange rate is matched

      Examples:
        | Currency  |
        |    USD    |
        |    EUR    |