Feature: First UI Test

  #Verify page title is “Ecommerce Website Template”

  Background: Open Page
    Given Shopify Page is Open PM

  Scenario: First UI test
    When I verify the page title PM
    Then I should see the page title "Ecommerce Website Template"

  Scenario: First UI test
    Given test