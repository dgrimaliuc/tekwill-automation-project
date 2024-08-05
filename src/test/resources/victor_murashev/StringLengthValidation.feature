Feature: String Length Validation

  Scenario: Validate the length of a string
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be "9"
