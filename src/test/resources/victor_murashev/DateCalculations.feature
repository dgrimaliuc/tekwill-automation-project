Feature: Date Calculations

  Scenario: Calculate difference in days between two dates
    Given I have the date "2022-06-01"
    And I have the date "2023-07-15"
    When I calculate the difference in days
    Then the difference should be 1 year 1 month and 14 days
