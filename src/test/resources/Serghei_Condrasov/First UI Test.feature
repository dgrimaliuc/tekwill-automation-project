Feature: Fist UI Test

  Background: Open page
    Given Open Random location

  Scenario: Verify location text
    Then Verify random location text is present in [Text Input], [Pets in ..] and [Adoptions in ..]
    Then Verify current section contains default info in [The game]