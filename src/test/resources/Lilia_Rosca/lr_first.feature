Feature: My first test

  Scenario: Validarea ca "Hello folks!" contine "Hello"   // Ex1
    Given I have a string "Hello folks!"
    When I check if it contains "Hello"
    Then It should return true

  Scenario: Reverse a string    // Ex 2
    Given I have a string "Hello"
    When I reverse the string
    Then The reversed string should be "olleH"

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be 9