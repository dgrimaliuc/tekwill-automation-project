Feature: Simple UI Tests

  Scenario Outline: Open the Pet Store web app
    Given Open PetStore
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title
    Examples:
      | title |
      | Plett |

#  Home Work,  Automate next test:
#  1. Open the page https://petstore-eb41f.web.app/?location=Chisinau
#  2. Verify text in The info section contains
#       Pets in Chisinau: 4
#       Adoptions in Chisinau: 2

  Scenario Outline: HW. Open the Pet Store web app in Chisinau
    Given Open PetStore in "<city>"
    Then I see Pets in "<city>" 2
    Then I see Adoption in "<city>" 2
    Examples:
      | city |
      | Chisinau |