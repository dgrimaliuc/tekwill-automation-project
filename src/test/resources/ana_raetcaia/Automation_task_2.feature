Feature: Automation test2

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be "9"

  Scenario: String contains validation
    Given I have a string "Java Help"
    Then the string should contain "He"

  Scenario: Even number validation
    Given I have a number "10"
    Then the number should be even

  Scenario: Odd number validation
    Given I have a number "7"
    Then the number should be odd

  Scenario: List size validation
    Given I have a list with elements
    |Apple|
    |Banana|
    |Orange|
    When I check the size of the list
    Then the size of the list should be "3"