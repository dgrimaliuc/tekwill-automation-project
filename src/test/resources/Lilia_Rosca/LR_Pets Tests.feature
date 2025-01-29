Feature: Pets tests

  Scenario: Add a pet test
    Given Open a random location
    Given Add a pet

  Scenario: Add a pet with name
    Given Open a random location
    When Add a pet with name
    Then I see the pet with name added
# 27.01
  Scenario: Hover Add pet button test
    Given Open a random location
    Then I hover the Add pet button it is highlighted
# HW 27.01
  Scenario: Hover Adopt Selected pets button test
    Given Open a random location
    Then I hover the Adopt Selected pets button it is highlighted

  Scenario: Deselect Pet button test
    Given Open a random location
    When Add a pet with name
    When I select a pet
    Then Button is enabled and pet is selected
    When I deselect the same pet
    Then Button is disabled and pet is not selected
