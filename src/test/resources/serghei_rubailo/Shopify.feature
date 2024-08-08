Feature: UI Test
  Background: Open Page
    Given Shopify Page is Open

  Scenario: Price under $25
    When Select price under $25
    Then Verify price is under $25

  Scenario: Check page title
    Then Page Title is "Ecommerce Website Template"