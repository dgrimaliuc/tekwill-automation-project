Feature: First UI Test

  Background: Open Page
    Given Adopt Page is Open

  Scenario Outline: Verify Location can be changed
    When Change location to "<new_location>"
    Then Verify location changed "<new_location>"
    
    Examples:
      | new_location |
      | Belt         |


