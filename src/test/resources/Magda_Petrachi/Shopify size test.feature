Feature: Shopify size test

  Background: Open the Shopify test
    Given I open Shopify page

  Scenario: S size test
    When I select S size in filter
    Then I should see product with "S" size

  Scenario: M size test
    When I select M size in filter
    Then I should see product with "M" size

  Scenario: L size test
    When I select L size in filter
    Then I should see product with "L" size

  Scenario: XL size test
    When I select XL size in filter
    Then I should see product with "XL" size

