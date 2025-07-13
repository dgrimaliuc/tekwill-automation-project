Feature: Shopify tests
Background: Open the Shopify page
  Given I open the Shopify page

Scenario: Open the Shopify page
    Then I should see the Shopify homepage

Scenario: Under 25$ filter price test
  When I select the Under 25$ filter
  Then I should see products with price under 25$

Scenario: Over 100$ filter price test
  When I select the Over 100$ filter
  Then I should see products with price Over 100$

Scenario: 25$ to 50$ filter price test
  When I select the filter from 25$ to 50$
  Then I should see products with price from 25$ to 50$

Scenario: 50$ to 100$ filter price test
  When I select the filter from 50$ to 100$
  Then I should see products with price from 50$ to 100$

Scenario: The under $25 and over $100 filters price test
  When I select the Under 25$ filter
  When I select the Over 100$ filter
  Then I should see products with price under $25 and over $100








  Scenario: Open the Elefant page
    Given I open the Elefant page
    Then I should see the Elefant homepage