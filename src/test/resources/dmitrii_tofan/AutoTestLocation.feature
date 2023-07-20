Feature: Auto Test Location

  Scenario Outline: Test custom location

    Given If custom location is open "<custom location>"
    Then Verify custom location "<custom location>"
    And Verify pets in section title "<custom location>"
    Then Verify Adopt in section title "<custom location>"
    Then  Verify Default text in the game section
    Examples:
      |custom location|
      |Centre         |