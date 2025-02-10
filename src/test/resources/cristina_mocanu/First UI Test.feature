Feature: Simple UI tests

  Scenario: Verify the info section
    Given Open Pet Store web app
    Then I see "Chisinau" in the location input
    Then I see "Pets in Chisinau: 4" in Info Section title
    Then I see "Adoptions in Chisinau: 2" in Info Section title