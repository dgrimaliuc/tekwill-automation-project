Feature: Python_executer

  Scenario: String length validation
    Given I have a string "Java Help" MR
    When I check the length of the string MR
    Then the length should be "9" MR


  Scenario: String contains validation
    Given I have a string "Java Help" MR
    Then the string should contain "He" MR


  Scenario: Even number validation
    Given I have a number "10" MR
    Then the number should be even MR


  Scenario: Odd number validation
    Given I have a number "7" MR
    Then the number should be odd MR


  Scenario: List size validation
    Given I have a list with elements MR
      | Apple  |
      | Banana |
      | Orange |
    When I check the size of the list MR
    Then the size of the list should be "3" MR
