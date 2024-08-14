Feature: Size Filters Tests


  Background: Open Page
    Given Shopify Page is Open PM3

  Scenario Outline: Black color test
    When Select size "<size>"
    Then Verify size is "<size>"

    Examples:
      | size |
      | S    |
      | M    |
      | L    |
      | XL   |

  Scenario: Combine size filters test
    When Select size "M"
    When Select size "L"
    Then Verify size is M and L