Feature: Homework Tests
  Scenario: String length validation
    Given I have a string “Java Help”
    When I check the length of the string
    Then the length should be “9"