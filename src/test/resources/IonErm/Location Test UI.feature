Feature: Location Test

  Scenario Outline: Change location button test
    Given Open the PetStore
    Then Change location to "<title>"
    Then I see "<title>" in location input
    Then I see "<title>" Pet section title
    Then I see "<title>" in Adoptions Section title
    Examples:
      | title   |
      | Chicago |

  Scenario: Change location button test 2
    Given Open random location
    Then I see the correct location name in location input
    Then I see the correct location name inPet section title
    Then I see the correct location name in in Adoptions Section title

  Scenario: Open in new tab button test
    Given Open random location
    Then I open random location in new tab
    Then I see the correct location name in location input
    Then I see the correct location name inPet section title
    Then I see the correct location name in in Adoptions Section title

