
Feature: My First Test


  Scenario: Test if Hello World includes Hello
Given I have a string "Hello World"
When I check if it contains "Hello"
Then I should return true


  Scenario: Count length of paragraph
    Given I have the following paragraph:
    """
  This is a test paragraph. It has several sentences
  Each sentence has multiple words.
  """
    When I count the length of string
    Then the length should be 84


  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

  Scenario: Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario: Even number validation
    Given I have a number “10”
    Then the number should be even