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