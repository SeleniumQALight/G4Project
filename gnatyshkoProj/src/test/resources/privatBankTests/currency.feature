Feature: Currency Feature

  @R005
  Scenario Outline: R005 Compare currency value between API and UI '<currency>'
    Given User receives '<currency>' currency value from api
    When User opens 'Privatbank' page
    Then User receives '<currency>' currency value from ui
    And User compares value from api with ui

    Examples:
      | currency |
      | USD      |
      | EUR      |
