Feature: Adopts page tests p2

  Background: Open window
    Given Open a Random location

  Scenario: Test Custom location
    Then Verify Text in Input field "{0}"
    Then Verify Pets in section title "{0}"
    Then Verify Adopt in section title "{0}"
    Then Verify Default text in The game section

  Scenario: Window can be opened in new tab test
    Then Verify new tab can be opened


  Scenario: Added pet in different location is not reflected in current one test
    Then Open a Random location
    Then Add a pet in current location
    And Verify pet is not added in previous location














