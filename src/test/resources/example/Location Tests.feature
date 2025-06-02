# Created by dgrimaliuc at 24.01.2025
Feature: Location tests
  # Enter feature description here

  Scenario Outline: Open the Pet Store webb app
    Given Open Petstore
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoptions Section title
    Examples:
      | title |
      | Plett |


  Scenario: Change location button test
    Given Open a random location
    Then I see correct location name in location input
    Then I see correct location name in Pets Section title
    Then I see correct location name in Adoptions Section title

  Scenario: Open in new tab button test
    Given Open a random location
    When I open a random location in new tab
    Then I see correct location name in location input
    Then I see correct location name in Pets Section title
    Then I see correct location name in Adoptions Section title

