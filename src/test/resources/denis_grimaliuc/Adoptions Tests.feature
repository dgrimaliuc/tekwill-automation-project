# Created by dgrimaliuc at 24.01.2025
Feature: Adoptions Tests

  Scenario: Create Adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    Then I see the pet with name adopted
