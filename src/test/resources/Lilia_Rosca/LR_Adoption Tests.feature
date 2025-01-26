Feature: Adoption tests

  Scenario: Create adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    Then I see the pet with name adopted