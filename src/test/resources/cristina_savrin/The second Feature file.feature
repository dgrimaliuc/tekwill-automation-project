Feature: Automation Task 2 - Using Python

  Scenario: String length validation
    Given I have a string "Java Help" CS
    When Execute Python Script "len('{0}')" CS
    Then Assert that "{1} == 9" CS

  Scenario: String contains validation
    Given I have a string "Java Help" CS
    Then the string should contain "He" CS


  Scenario: Even number validation
    Given I have a number "10" CS
    Then the number should be even CS

  Scenario: Odd number validation
    Given I have a number "7" CS
    Then the number should be odd CS

  Scenario: List size validation
    Given I have a list with elements CS
    | Apple |
    | Banana |
    | Orange |
    When Execute Python Script "len({0})" CS
    Then the size of the list should be "3" CS