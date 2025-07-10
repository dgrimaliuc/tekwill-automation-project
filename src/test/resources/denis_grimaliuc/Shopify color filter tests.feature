# Created by dgrimaliuc at 07.07.2025
Feature: Shopify color filter tests

  Background: Open the Shopify page
    Given I open the Shopify page

  Scenario: Black color test
    When I select the Black color filter
    Then I should see products with "Black" color

  Scenario: White color test
    When I select the White color filter
    Then I should see products with "White" color

  Scenario: Red color test
    When I select the Red color filter
    Then I should see products with "Red" color

  Scenario: Red and Black colors test
    When I select the Red color filter
    When I select the Black color filter
    Then I should see products with "Red" and "Black" colors
