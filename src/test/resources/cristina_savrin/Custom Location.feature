Feature: Custom Location Check

  Background: Open AdoptPage
    Given Open AdoptPage with random location

  Scenario: Verify custom location
    Then Verify random location "{0}" in [Text Input], [Pets in ..] and [Adoptions in ..]
    And Verify [The game] section for random location "{0}"

  Scenario: Add a pet
    Given Add "1" random pets
    Then Verify default text disappears

  Scenario: Add several pets
    Given Add "5" random pets
    Then Verify [The game] section for random location "{0}"
    And Check [Adopt Selected Pets] and [Deselect] buttons are disabled by default
    And Adopt "1" pets