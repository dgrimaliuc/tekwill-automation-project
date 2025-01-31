Feature: My first test

  Scenario: Test if Hello World includes Hello
    Given I have a string "Hello World"
    When I check if it contains "Hello"
    Then It should returns true

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be 9

  Scenario: Count the words in a paragraph
    Given I have the following paragraph:
    """
    This is a test paragraph. It has several sentences.
    Each sentence has multiple words.
    """
    When I count the number of words
    Then the word count should be 14

  Scenario: List contains lemon
    Given I have a list with elements
    | Cherry|
    | Grenade|
    | Lemon |
    | Apple  |
    When I check if list contains "Lemon"
    Then list should contain lemon

  Scenario: Count length of paragraph
    Given I have different paragraph:
    """
    This is a test paragraph. It has several sentences.
    Each sentence has multiple words.
    """
    When I count the length of string
    Then the length must to be 85

  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

  Scenario: Even number validation
    Given I have a number “10”
    Then the number should be even

  Scenario: Add two numbers
    Given I have two numbers 5 and 3
    When I add the numbers
    Then the result must be 8