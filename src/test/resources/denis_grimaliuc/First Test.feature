# Created by dgrimaliuc at 15.01.2025
Feature: My first test
  # Enter feature description here

  Scenario: Test if Hello World includes Hello
    Given I have a string "Hello World!"
    When I check if it contains "Hello"
    Then It should return true


  Scenario: Reverse a string
    Given I have a string "Hello"
    When I reverse the string
    Then the reversed string should be "olleH"

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be 9

  Scenario: Count words in a paragraph
    Given I have the following paragraph:
      """
      This is a test paragraph. It has several sentences.
      Each sentence has multiple words.
      """
    When I count the number of words
    Then the word count should be 14


  Scenario: List contains banana
    Given I have a list with elements
      | Apple  |
      | Banana |
      | Orange |
      | Apple  |
    Then list should contain "Banana"

  Scenario: Count length of paragraph
    Given I have the following paragraph:
      """
      This is a test paragraph. It has several sentences.
      Each sentence has multiple words.
      """
    When I count the length of string
    Then the length should be 85

  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

  Scenario: Even number validation
    Given I have a number 10
    Then the number should be even

  Scenario: Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario Outline: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | name  | John    |
      | age   | 30      |
      | email | <email> |
    When I retrieve the value for the key "email"
    Then the value should be "<email>"
    Examples:
      | email            |
      | john@example.com |

