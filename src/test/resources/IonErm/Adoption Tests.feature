Feature: Adoption tests

  Scenario: Create Adoption test
    Given Open random location
    Given Add a pet with name
    Given Create adoption
    Then I see the pet with name adopted