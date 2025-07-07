# Created by dgrimaliuc at 07.07.2025
Feature: Shopify color filter tests

  Background: Open the Shopify page
    Given I open the Shopify page

  Scenario: Black color test
    When I select the Black color filter
    Then I should see products with "Black" color
