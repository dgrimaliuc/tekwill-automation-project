# Created by dgrimaliuc at 07.07.2025
Feature: Shopify size filter tests

  Background: Open the Shopify page
    Given I open the Shopify page


  Scenario: S size test
    When I select the S size filter
    Then I should see products with "S" size
#
  Scenario: M size test
    When I select the M size filter
    Then I should see products with "M" size
#
  Scenario: XL size test
    When I select the XL size filter
    Then I should see products with "XL" size
#
  Scenario: XL and XXL sizes test
    When I select the S size filter
    When I select the XL size filter
    Then I should see products with "S" and "XL" sizes
