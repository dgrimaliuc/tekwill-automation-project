
Feature: Price Filters Tests
  # Enter feature description here

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
