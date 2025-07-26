Feature: Shopify color filter tests

  Background: Open the Shopify page
    Given I open the Shopify page

  Scenario: Open the Shopify page
    Then I should see the Shopify homepage

  Scenario: Black color test
    When I select the Black color filter
    Then I should see products with Black color
    Then I should see products with "Black" color

  Scenario: White color test
    When I select the White color filter
    Then I should see products with White color

  Scenario: Green color test
    When I select the Green color filter
    Then I should see products with Green color

  Scenario: Purple color test
    When I select the Purple color filter
    Then I should see products with Purple color