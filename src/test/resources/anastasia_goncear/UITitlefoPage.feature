Feature: Verify Shopify page title

  Scenario: The page title should be "Ecommerce Website Template"
    Given I open the Shopify homepage
    Then the page title should be "Ecommerce Website Template"