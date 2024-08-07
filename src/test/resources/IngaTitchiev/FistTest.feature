Feature: Hello word feature

  Scenario: Check for Hello
    Given I have a string "Hello world"
    Then I should see "Hello"

  Scenario: String length validation
    Given I have a string "Java Help"
    When I check the length of the string
    Then the length should be "9"

  Scenario: Add two numbers
    Given I have the numbers 5 and 3
    When I add the numbers
    Then the result should be 8

  Scenario: Count words in a paragraph
    Given I have the following paragraph:
  """
  This is a test paragraph. It has several sentences.
  Each sentence has multiple words.
  """
    When I count the length of string
    Then the length should be 85

  Scenario: List size validation
    Given I have a list with elements
      |"Apple"|
      |"Banana"|
      |"Orange"|
      | "Apple"|
    When I check the size of the list
    Then the size of the list should be 4

  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | key   | value  |
      | name  | John   |
      | age   | 30     |
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

  Scenario: Convert comma-separated string to list of integers
    Given I have a comma-separated string "1,2,3,4,5"
    When I split and convert the string to a list of integers
    Then the list should be:
      | 1 |
      | 2 |
      | 3 |
      | 4 |
      | 5 |
  Scenario: Calculate difference in days between two dates
    Given I have the date "2022-06-01"
    And I have the date "2023-07-15"
    When I calculate the difference in days
    Then the difference should be 1 year 1 month and 14 days

