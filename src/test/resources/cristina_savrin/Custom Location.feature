Feature: Custom Location Check

  Scenario: Verify custom location
    Given Open AdoptPage with random location
    Then Verify random location "{0}" in [Text Input], [Pets in ..] and [Adoptions in ..]
    And Verify [The game] section