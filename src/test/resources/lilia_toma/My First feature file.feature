Feature: My first feature file

  Scenario: Addition of two numbers
    Given I have two numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario: Substraction of two numbers
    Given I have two numbers 10 and 4
    When I subtract the numbers
    Then the result should be 6

  Scenario: Concatenation of two strings
    Given I have two strings "Hello" and "World"
    When I concatenate the strings
    Then the result should be "HelloWorld"

  Scenario: String length validation
    Given I have a string "OpenAI"
    When I check the length of the string
    Then the length should be 6

 Scenario: String contains validation
    Given I have a string "OpenAI"
    When I check if the string contains "AI"
    Then the string should contain "AI"

 Scenario: Even number validation
    Given I have a number 10
    When I check if the number is even
    Then the number should be even