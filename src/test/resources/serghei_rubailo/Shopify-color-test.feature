Feature: Color Test

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Black color test
    When Select color "<color>"
    Then Verify color is "<color>"
    Examples:
      | color |
      | Black |
      | White |
      | Red |
      | Yellow |
      | Green |
      | Blue |
      | Purple |
      | Gray |
      | Beige |
      | Brown |

  Scenario: Select two different colors and verify that cards are displayed correctly
    When Select color "Green"
    When Select color "Purple"
    Then Verify colors are "Green" or "Purple"