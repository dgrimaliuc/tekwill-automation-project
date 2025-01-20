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
