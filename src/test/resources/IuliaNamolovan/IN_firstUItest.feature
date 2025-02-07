Feature: Simple UI test
  Scenario Outline: Open the Pet Store webb app
    Given Open Petstore
    Then  I see "<title>" in location input
    Then  I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title

    Examples:
      | title |
      | Plett |

  Scenario: Verify text in The info section
    Given Open Petstore in "Chisinau"
    Then  I validate the info section
