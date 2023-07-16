Feature: First UI Test

  Background: Open page
    Given Adopt Page is open

  Scenario Outline: Verify location
    When Open AdoptPage with custom location "<new_location>"
    Then Verify presence of "<new_location>" in input text
    Then Verify presence of "<new_location>" in pets
    Then Verify presence of "<new_location>" in adoptions
    Then Verify section [The game]
    Examples:
      |new_location|
      |Orhei       |







