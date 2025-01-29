Feature: Adoption tests

  Scenario: Create adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    Then I see the pet with name adopted
# 27.01
  Scenario: Approve adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Approve first adoption
    Then I see the pet adopted
    When I reload page
    Then Pets and Adoptions are empty

  Scenario: Deny adoption test
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Deny first adoption
    Then I see pet not adopted
    When I reload page
    Then Adoption section is empty

  Scenario: Adopt an already adopted pet
    Given Open a random location
    When Add a pet with name
    And Create adoption
    And Adopt first pet
    Then Adoption with rejected status is displayed
    When I reload page
    Then Rejected adoption is not displayed
