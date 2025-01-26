Feature: Pets tests

  Scenario: Add a pet test
    Given Open a random location
    Given Add a pet

  Scenario: Add a pet with name
    Given Open a random location
    When Add a pet with name
    Then I see the pet with name added
