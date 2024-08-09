Feature: Multi-Color Filters Tests

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Filtering multiple colors test
    When Select colors "<color1>" and "<color2>"
    Then Verify colors are "<color1>" and "<color2>"

    Examples:
      | color1 | color2 |
      | Black  | Blue   |
      | Red    | Black  |
      | Blue   | Red    |
