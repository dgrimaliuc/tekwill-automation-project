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

  Scenario: Hover Add pet button test
    Given Open a random location
    Then I hover Add pet button it is highlighted

  Scenario:  Deselect Pet button test
    Given Open a random location
    When Add a pet with name
    When I select a pet
    Then Button is enabled and pets are selected
    And When I deselect the same pet
    Then Button is disabled and pets are not selected
