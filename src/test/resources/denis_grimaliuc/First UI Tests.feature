# asdasdas
Feature: Simple UI tests

  Scenario Outline: Open the Pet Store webb app
    Given Open Petstore
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoptions Section title
    Examples:
      | title |
      | Plett |


  Scenario: Validate the info section
    Given Open Petstore in "Chisinau"
    Then I validate the info section


  Scenario Outline: Change location button test
    Given Open Petstore
    When Change location to "<title>"
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoptions Section title

    Examples:
      | title   |
      | Falesti |

