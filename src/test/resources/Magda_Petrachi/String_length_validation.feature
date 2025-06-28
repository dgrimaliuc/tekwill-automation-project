Feature: MP second HM of testing

  Scenario: List size validation
    Given I have a list with elements MP
      | “Apple”  |
      | “Banana” |
      | “Orange” |
      | “Apple”  |
    When I check the size of the list MP
    Then the size of the list should be 4 MP


  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs: MP
      | key   | value  |
      | name  | John   |
      | age   | 30     |
      | email | john@example.com |
    When I retrieve the value for the key "email" MP
    Then the value should be "john@example.com" MP


#  String Parsing and Conversion
  Scenario: Convert comma-separated string to list of integers
    Given I have a comma-separated string "1,2,3,4,5" MP
    When I split and convert the string to a list of integers MP
    Then the list should be: MP
      | 1 |
      | 2 |
      | 3 |
      | 4 |
      | 5 |