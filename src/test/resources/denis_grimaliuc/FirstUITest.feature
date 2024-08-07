Feature: First UI Test

  Background: Open Page
    Given Shopify Page is Open

  Scenario: Price under $25
    When Select price under $25
    Then Verify price is under $25

  Scenario: Price under $25 Test
    Then Verify page title


