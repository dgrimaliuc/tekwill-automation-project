# Created by dgrimaliuc at 24.01.2025
Feature: Pets tests
  # Enter feature description here

  Scenario: Add a pet test
    Given Open a random location
    Given Add a pet


  Scenario: Add a pet with name test
    Given Open a random location
    When Add a pet with name
    Then I see the pet with name added
