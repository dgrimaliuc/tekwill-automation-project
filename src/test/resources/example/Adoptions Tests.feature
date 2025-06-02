# Created by dgrimaliuc at 24.01.2025
Feature: Adoptions Tests

  Scenario: Create Adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    Then I see the pet with name adopted

  Scenario: Approve adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Approve first adoption
    Then I see pet adopted
    When I reload page
    Then Pet and Adoptions sections are empty

  Scenario: Deny adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Deny first adoption
    Then I see pet not adopted
    When I reload page
    Then Adoptions sections is empty

  Scenario: Adopt a already adopted pet test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Adopt the firs Pet
    Then Adoption with rejected status is displayed
    When I reload page
    Then Rejected adoption is not displayed
