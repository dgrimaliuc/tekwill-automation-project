Feature: Shopify color test

  Background: Open the Shopify test
    Given I open Shopify page

  Scenario: Black color test
      When I select the Black color filter
      Then I should see product with "Black" color


  Scenario: White color test
    When I select the White color filter
    Then I should see product with "White" color