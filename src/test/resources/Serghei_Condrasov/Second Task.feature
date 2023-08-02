Feature: Second Task

  Scenario: String length validation
    Given I have a string "Java Help" SC
    When I check the length of the string SC
    Then the length should be "9" SC

  Scenario: String contains validation
    Given I have a string "Java Help" SC
    Then the string should contain "He" SC

  Scenario: Even number validation
    Given I have a number "10" SC
    Then the number should be even SC

  Scenario: Odd number validation
    Given I have a number "7" SC
    Then the number should be odd SC

  Scenario: List size validation
    Given I have a list with elements SC
      | Apple  |
      | Banana |
      | Orange |
    When I check the size of the list SC
    Then the size of the list should be "3" SC
