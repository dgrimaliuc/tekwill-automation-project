Feature: Automation task # 5

  Background: Open Page
    Given New Adopt Page with random location is open

    Scenario: Check that A pet with an specific Name can be added to [Pets in <Location>] section
      When A random pet name is entered and Add Rescue button is clicked
      Then Default text disappears when a pet is added
      Then [Adopt selected] and [Deselect] buttons are disabled when pets are not selected

    Scenario: [The game section] is updated
        When several pets are added: 3

    Scenario: Verify the status of adopted pet is changed to "ONHOLD"
      When A pet is selected and [Adopt selected pets] button is clicked
