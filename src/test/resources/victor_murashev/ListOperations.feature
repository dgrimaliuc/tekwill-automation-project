Feature: List Operations

  Scenario: List size validation
    Given I have a list with elements
      | Apple  |
      | Banana |
      | Orange |
      | Apple  |
    When I check the size of the list
    Then the size of the list should be 4
