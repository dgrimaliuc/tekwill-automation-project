Feature: Map Operations

  Scenario: Retrieve value from a map
    Given I have a map with the following key-value pairs:
      | key   | value            |
      | name  | John             |
      | age   | 30               |
      | email | john@example.com |
    When I retrieve the value for the key "email"
    Then the value should be "john@example.com"
