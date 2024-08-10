Feature: Sort filter

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: tests for sorting ascending and descending
    When Sorting is selected "<option>"
    Then Verify that elements sorted in "<option>" order

    Examples:
      | option |
      | Ascending |
      | Descending |
