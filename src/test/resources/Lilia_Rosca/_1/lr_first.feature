Feature: My first test
# Ex 1
  Scenario: Validarea ca "Hello folks!" contine "Hello"
    Given I have a string "Hello folks!"
    When I check if it contains "Hello"
    Then It should return true
# Ex 2
  Scenario: Reverse a string
    Given I have a string "Hello"
    When I reverse the string
    Then The reversed string should be "olleH"
# Ex 3
  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be 9

# Ex 4 multiline string
  Scenario: Count words in a paragrath
    Given I have the following paragraph:
    """
    This is a test paragrath. It has several sentences.
    Each sentence has multiple words.
    """
    When I count the number of words
    Then the word count should be 14

# Ex 5 contains a string
  Scenario: List contains banana
    Given I have a list with elements
    |Apple|
    |Banana|
    |Orange|
    |Apple|
 #   When I check if list contains "Banana"  - daca in 2 pasi
 #   Then list should contain banana
    Then list should contain "Banana"

# HW 17.01
# Ex 1
  Scenario: Count length of paragraph
    Given I have the following paragraph:
  """
  This is a test paragraph. It has several sentences.
  Each sentence has multiple words.
  """
    When I count the length of string
    Then the length should be 85

# Ex 2
  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

# Ex 3
  Scenario: Even number validation
    Given I have a number 10
    Then the number should be even
# Ex 4
  Scenario: Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8
# Ex 5
  Scenario: List size validation
    Given I have a list with elements
      | Apple	|
      | Banana	|
      | Orange	|
      | Apple	|
    When I check the size of the list
    Then the size of the list should be 4
# Ex 6
  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | name  | John   |
      | age   | 30     |
      | email | john@example.com |
    When I retrieve the value for the key "email"
    Then the value should be "john@example.com"