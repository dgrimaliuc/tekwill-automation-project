Feature: Automation Task 2 - Using Python

  Scenario: String length validation
    Given I have a string "Java Help" CS
    When Execute Python Script "len('{0}')" CS
    Then Assert that "{1} == 9" CS

  Scenario: String contains validation
    Given I have a string "Java Help" CS
    Then Execute Python Script "'He' in '{0}'" CS


  Scenario: Even number validation
    Given I have a number "10" CS
    Then Assert that "{0} % 2 == 0" CS


  Scenario: Odd number validation
    Given I have a number "7" CS
    Then Assert that "{0} % 2 == 1" CS

  Scenario: List size validation
    Given I have a list with elements CS
    | Apple |
    | Banana |
    | Orange |
    When Execute Python Script "len({0})" CS
    Then Assert that "{1} == 3" CS