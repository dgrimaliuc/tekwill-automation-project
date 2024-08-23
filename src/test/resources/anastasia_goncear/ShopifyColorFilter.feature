Feature: Color Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open AN

  Scenario Outline: Filtering color test
    When Select a color "<color>"
    Then Verify the color is "<color>"

    Examples:
      | color |
      | Black |
      | Blue  |
      | Red   |