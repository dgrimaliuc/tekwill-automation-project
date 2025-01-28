Feature: Simple UI Tests

  Scenario Outline: Open the Pet Store web app
    Given Open PetStore
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title
    Examples:
      | title |
      | Plett |
