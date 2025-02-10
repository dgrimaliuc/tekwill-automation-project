Feature: Simple UI tests

Scenario Outline: Open the Pet store web app
  Given Open Petstore
  Then I see "<title>" in location input
  Then I see "<title>" in Pets Section title
  Then I see "<title>" in Adoptions Section title
  Examples:
  |title|
  |Plett|

  Scenario: Open the Pet store web app
    Given Open Petstore web
    Then Verify Pets in Chisinau: 5
    Then Verify Adoptions in Chisinau: 1