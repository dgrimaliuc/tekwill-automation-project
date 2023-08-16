@Run
Feature: Second task

  Scenario: String length validation
    Given I have a string "Java Help"
    When Check the length of the string
    Then The string length should be 9


  Scenario: String contains validation
    Given I have a string "Java Help"
    Then String should contain "He"

  Scenario: Even number validation
    Given I have a number 10
    Then The number should be even

  Scenario: Odd number validation
    Given I have a number 7
    Then The number should be odd

  Scenario: List size validation
    Given I have a list with elements
      |Apple|
      |Banana|
      |Orange|
    When I check the size of the list
    Then The size of the list should be 3