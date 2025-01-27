Feature: Location tests

  Scenario: Change location and open new tab
    Given Open the app ""
    Then Put a random string and change location
    Then I see input location
#    Then Open in new tab