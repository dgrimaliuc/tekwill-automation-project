Feature: String Parsing and Conversion

  Scenario: Convert comma-separated string to list of integers
    Given I have a comma-separated string "1,2,3,4,5"
    When I split and convert the string to a list of integers
    Then the list should be:
      | 1 |
      | 2 |
      | 3 |
      | 4 |
      | 5 |
