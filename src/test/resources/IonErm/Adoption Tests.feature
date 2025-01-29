Feature: Adoption tests

  Scenario: Create Adoption test
    Given Open random location
    Given Add a pet with name
    Given Create adoption
    Then I see the pet with name adopted

  Scenario: Approve adoption test.
    Given Open random location
    Given Add a pet with name
    Given Create adoption
    And Approve first adoption pet
    Then I see pet adopted
    When I reload page
    Then Pet and Adoption sections are empty

  Scenario: Deny adoption test
    Given Open random location
    Given Add a pet with name
    Given Create adoption
    When Deny first adoption
    And I see pet not adopted
    When Adoptions sections is empty

  Scenario: Adopt a already adopted pet test
    Given Open random location
    Given Add a pet with name
    Given Create adoption
    Given Adopt the firs Pet
    Then Adoption with rejected status is displayed
    When I reload page
    Then Rejected adoption is Not displayed
