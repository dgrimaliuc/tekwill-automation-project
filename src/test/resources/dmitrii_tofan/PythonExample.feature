Feature: Python example

  Scenario: String Python length validation

    Given DTA I have a string "OpenAI"
    When DTA Execute Python String "len('{0}')"
    Then DTA Assert that "{1} == 6"