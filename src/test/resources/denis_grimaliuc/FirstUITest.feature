Feature: First UI Test

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Price under $25
    When Select price under "<price>"
    Then Verify price is under $25

    Examples:
      | price     |
      | Under $25 |


