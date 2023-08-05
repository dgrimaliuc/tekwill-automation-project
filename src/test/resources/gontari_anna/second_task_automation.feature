Feature: second task automation

  Scenario: String length validation
    Given I have one string "Java Help"
    When Checking string length
    Then The string length should be "9"

    Scenario: String contains validation
      Given I have one string "Java Help"
      Then GA string should contain "He"

    Scenario: Even number validation
      Given GA has number "10"
      Then GA number should be even

    Scenario: Odd number validation
      Given GA has number "7"
      Then GA number should be odd

    Scenario: List size validation
      Given GA has a list with elements
      |Apple|
      |Banana|
      |Orange|
      When GA checks the size of the list
      Then GA size of the list should be "3"