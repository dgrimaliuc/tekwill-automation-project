Feature: UITest_First

  Background: Open a Page
    Given Open Page with random location

  Scenario: Verify selected location
    Then Verify the text is present in [Text Input] "{0}"
    Then Verify the text is present in [Pets in ..] "{0}"
    And Verify the text is present in [Adoptions in ..] "{0}"
    And Verify current section contains default info in [The game] section "{0}"