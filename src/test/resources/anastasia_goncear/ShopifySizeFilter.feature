Feature: Price Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open AN

  Scenario Outline: Black color test
    When Select products size "<size>"
    Then Verify products size is "<size>"

    Examples:
      | size |
      | S    |
      | M    |
      | L    |
      | XL   |