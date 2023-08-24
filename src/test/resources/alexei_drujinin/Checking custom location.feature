@Run
  Feature: Checking custom location

    Background: Open AdoptPage
      Given Open AdoptPage with custom location

    Scenario: Verify location text
      Then Verify random location text is present in [Text Input], [Pets in ..] and [Adoptions in ..]
      Then Verify current section contains default info in [The game]