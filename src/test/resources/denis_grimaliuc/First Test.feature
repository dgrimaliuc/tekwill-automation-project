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

