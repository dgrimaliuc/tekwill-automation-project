Feature: Gender Test

  Background: Open Page
    Given Shopify Page is Open

 Scenario Outline: Gender Test
   When Select gender "<gender>"
   Then Verify gender is "<gender>"
   Examples:
     | gender |
     | Male |
     | Female |

  Scenario: Select two different genders and verify that cards are displayed correctly
    When Select gender "Male"
    When Select gender "Female"
    Then Verify genders are "Male" or "Female"