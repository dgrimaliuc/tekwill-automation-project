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

    #Homework
  Scenario: Count length of paragraph
    Given I have the following paragraph:
  """
  This is a test paragraph. It has several sentences.
  Each sentence has multiple words
  """
    When I count the length of string
    Then the length should be 84
    When I count the number of words
    Then the word count should be 14

  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

  Scenario: Even number validation
    Given I have a number '10'
    Then the number should be even

  Scenario: Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario: List size validation
    Given I have a list with elements
      | Apple  |
      | Banana |
      | Orange |
      | Apple  |
      #| Apple  |
    When I check the size of the list
    Then the size of the list should be 4

  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | key   | value            |
      | name  | John             |
      | age   | 30               |
      | email | john@example.com |
    When I retrieve the value for the key "email"
    Then the value should be "john@example.com"