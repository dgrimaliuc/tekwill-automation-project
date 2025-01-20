# asdasdas
Feature: Simple UI tests

  Scenario: Open the Pet Store webb app
    Given Open Petstore
    Then I see "Plett" in location input
    Then I see "Plett" in Pets Section title
    Then I see "Plett" in Adoptions Section title
