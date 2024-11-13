Feature: Hello World Feature

  Scenario: Check for Hello
    Given I have a string "Java Help"
    Then The string should contain "He"

  Scenario: Reverse a string
    Given I have a string "Hello"
    When I reverse the string
    Then the reversed string should be "olleH"

  Scenario: Subtract two numbers
    Given I have the numbers 10 and 4
    When I subtract the second number from the first
    Then The result should be 6

  Scenario: Even number validation
    Given I have a number 10
    Then The number should be even

  Scenario: Count words in a paragraph
    Given I have the following paragraph:
      """
      This is a test paragraph.   It has several sentences.
      Each sentence has multiple words.
      """
    When I count the number of words
    Then the word count should be 14

  Scenario: Sort a list of integers
    Given I have the following list of integers:
      | 3 |
      | 1 |
      | 4 |
      | 5 |
      | 2 |
    When I sort the list
    Then the sorted list should be:
      | 1 |
      | 2 |
      | 3 |
      | 4 |
      | 5 |


  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | key   | value            |
      | name  | John             |
      | age   | 30               |
      | email | john@example.com |
    When I retrieve the value for the key "email"
    Then the value should be "john@example.com"


  Scenario: Calculate total price of products
    Given I have the following products:
      | Product Name | Price |
      | Apple        | 2     |
      | Banana       | 1     |
      | Cherry       | 3     |
    When I calculate the total price
    Then the total price should be 6


  Scenario: Calculate difference in days between two dates
    Given I have two dates "2023-07-01" and "2023-07-15"
    When I calculate the difference in days
    Then the difference should be 14 days
































