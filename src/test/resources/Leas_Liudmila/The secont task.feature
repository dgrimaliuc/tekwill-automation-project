Feature: The second task

  Scenario: String length validation
    Given LL I have a string "Java Help"
    When LL I check the length of the string
    Then LL the length should be "9"


Scenario: String contains validation
Given LL I have a string "Java Help"
Then LL the string should contain "He"


Scenario: Even number validation
Given LL I have a number "10"
Then LL the number should be even


Scenario: Odd number validation
Given LL I have a number "7"
Then LL the number should be odd


  Scenario: List size validation
    Given LL I have a list with elements
      | Apple |
      | Banana|
      | Orange|
    When LL I check the size of the list
    Then LL the size of the list should be "3"
