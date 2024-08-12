Feature: Price Filters Tests


  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Filtering color test
    When Select color "<color>"
    Then Verify color is "<color>"

    Examples:
      | color |
      | Black |
      | Blue  |
      | Red   |

  Scenario: Combine color filters test
    When Select color "Gray"
    When Select color "Brown"
    Then Verify color is Gray and Brown