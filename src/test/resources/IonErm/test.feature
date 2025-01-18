Feature: My first test

  Scenario: Check the statement "Hello, world!" contain "Hello"
    Given I have a string "Hello, world!"
    When I check if it contains "Hello"
    Then I should return true

  Scenario: Check the reverse of string
    Given I have a string "Hello"
    When I reverse the string
    Then The reversed string should be "olleH"

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be 9