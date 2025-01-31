Feature: AR First Test


  Scenario: Use different types Multiline String
    Given I defined a multiline string
      """
      Hi all,
      Could you please approve the below?
      Used special characters "~!@#$%^&*()+}{":?><,,/;'[]\"
      """

  Scenario: List
    Given I defined a list
      | string 1 |
      | string 2 |
      | string 3 |

  Scenario: Map
    Given I defined a map
      | Key1 | Value 1 |
      | Key2 | Value 2 |
      | Key3 | Value 3 |
      | Key4 | Value 4 |

  Scenario: List of Map
    Given I defined a list of map
      | Key 1    | Key 2    | Key 3    |
      | Value A1 | Value A2 | Value A3 |
      | Value B1 | Value B2 | Value B3 |
      | Value C1 | Value C2 | Value C3 |


  Scenario: Validate that string Hello World contains the word Hello
    Given I Have a string "Hello World!"
    When I check if it contains "Hello"
    Then It should return true

  Scenario: Reverse a string
    Given I Have a string "Hello World!"
    When I reverse the string
    Then the reversed string should be "!dlroW olleH"

  Scenario: String length validation
    Given I Have a string "Hello World!"
    When I check the length of the string
    Then the length must to be 12

  Scenario: Count words in a paragraph
#  //Spaces are counted, why???
    Given I have the following paragraph:
      """
      Hi all,
      This is a test paragraph.
      Used special characters "~!@#$%^&*()+}{":?><,,/;'[]\"
      """
    When I count the number of words
    Then The word count should be 11

  Scenario: List contains Banana
    Given I have a list with elements
      | Apple  |
      | Banana |
      | Orange |
      | Apple  |
#    When I check if list contains "Banana"
    Then The list should contain "Banana"


    #Home Work
  Scenario: HW1. Count length of paragraph
    Given I have the following paragraph:
  """
  This is a test paragraph. It has several sentences.
  Each sentence has multiple words.
  """
    When I count the length of string
    Then the length should be 85

  Scenario: HW2. Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then the result should be 6

  Scenario: HW3. Even number validation
    Given I have a number “10”
    Then the number should be even


    #Practice
  Scenario: HWP1. Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario: HWP2. List size validation
    Given I have a list with elements
      | Apple  |
      | Banana |
      | Orange |
      | Apple  |
    When I check the size of the list
    Then the size of the list should be 4

  Scenario Outline: HWP3. Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | name  | John    |
      | age   | 30      |
      | email | <email> |
    When I retrieve the value for the key "email"
    Then the value should be "<email>"
    Examples:
      | email            |
      | john@example.com |