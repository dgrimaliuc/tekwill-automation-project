Feature: HW Color/Size/Genders Filter Tests

  Background: Open Page
    Given Shopify Page is Open

Scenario: Select two different colors and verify that cards are displayed correctly
  When Select color "Black"
  When Select color "White"
  Then Verify colors is "Black" and "White"

Scenario: Select two different sizes and verify that cards are displayed correctly
  When Select size "M"
  When Select size "L"
  Then Verify sizes is "M" and "L"

Scenario: Select two different genders and verify that cards are displayed correctly
    When Select gender "Male"
    When Select gender "Female"
    Then Verify genders is "Male" and "Female"

Scenario Outline: Asc sorting test
  When I sort items by price
  When Select sorting "<sorting>"
  Then Verify sorting is "<sorting>"
  Examples:
    | sorting |
    | Ascending |
    | Descending |

