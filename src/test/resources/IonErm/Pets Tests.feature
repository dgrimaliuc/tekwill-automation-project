Feature: Pets Test

  Scenario: Add a pet test
    Given Open random location
    Given Add a pet

  Scenario: Add a pet with name test
    Given Open random location
    Given Add a pet with name
    Given I see the pet with name

  Scenario: Hover add pets button test
    Given Open random location
    Then I hover add pet button is highlighted

  Scenario: Deselect Pet button test
    Given Open random location
    Given Add a pet with name
    When I select a pet
    Then Button is enabled and Pets are selected
    And When I deselect the same pet
    Then Button is disabled and pets are not selected

  Scenario: Test adopt selected pets button
    Given Open random location
    Given Add a pet with name
    When I select a pet
    Then I hover adopt selected pets button is highlighted

