Feature: Custom Location Check

  Scenario: Verify custom location
    Given Open AdoptPage with custom location
    Then Verify custom location in [Text Input], [Pets in ..] and [Adoptions in ..]
    And Verify [The game] section