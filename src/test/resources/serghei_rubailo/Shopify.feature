Feature: UI Test
  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Price under $25
    When Select price <filter>
    Then Verify price is <filter>

    Examples:
      | filter |
      | "under $25" |

  Scenario: Check page title
    Then Page Title is "Ecommerce Website Template"