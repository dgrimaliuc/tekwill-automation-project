Feature: Multi-Sizes Filters Tests

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Filtering multiple sizes test
    When Select sizes "<size1>" and "<size2>"
    Then Verify sizes are "Size: <size1>" and "Size: <size2>"

    Examples:
      | size1 | size2 |
      | S     | M     |
      | M     | XL    |
      | L     | XL    |