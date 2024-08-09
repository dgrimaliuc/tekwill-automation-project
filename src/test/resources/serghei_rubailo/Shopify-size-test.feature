Feature: Size Test

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Test Size
    When Select size "<size>"
    Then Verify size is "<size>"
    Examples:
      | size |
      | S |
      | M |
      | L |
      | XL |

  Scenario: Select two different sizes and verify that cards are displayed correctly
    When Select size "M"
    When Select size "L"
    Then Verify sizes are "M" or "L"