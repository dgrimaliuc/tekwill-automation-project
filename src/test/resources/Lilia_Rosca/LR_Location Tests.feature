Feature: Location section tests

# class 22.01
  Scenario Outline: Change location button test
    Given Open Petstore
#    When Change location to a random
    When Change location to "<title>"
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title
    Examples:
      | title |
      | RandomCity |

# class 24.01 modificat la random location
  Scenario: Change location button test new version
    Given Open a random location
    Then I see correct location name in location input
    Then I see correct location name in Pets Section title
    Then I see correct location name in Adoption Section title

  Scenario: Open in new tab button test
    Given Open a random location
    When I open a random location in new tab
    Then I see correct location name in location input
    Then I see correct location name in Pets Section title
    Then I see correct location name in Adoption Section title