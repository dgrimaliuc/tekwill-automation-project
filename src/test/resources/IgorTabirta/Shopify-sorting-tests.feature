
Feature: Sorting Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Ascending sorting test
    When I sort items by price
    When Select sorting "<sorting>"
    Then Verify sorting is "<sorting>"

    Examples:
      | sorting   |
      | Ascending |



