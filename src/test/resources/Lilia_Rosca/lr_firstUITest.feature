Feature: My first UI test
  Scenario Outline: Open the Pet Store webb app
    Given Open Petstore
    #  Plett inlocuit <title>
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title
    Examples:
    | title |
    | Plett |

# HW Open the page https://petstore-eb41f.web.app/?location=Chisinau
#    Verify text in The info section contains Pets in Chisinau: 4 and Adoptions in Chisinau: 2
  Scenario: Validate the Info Section
    Given Open Petstore location "Chisinau"
    Then I validate the Info Section

# class 22.01
  Scenario Outline: Change location button test
    Given Open Petstore
#    When Change location to a random
    When Change location to "<title>"
    Then I see "<title>" in location input
    Then I see "<title>" in Pets Section title
    Then I see "<title>" in Adoption Section title
    Examples:
      | title |
      | RandomCity |