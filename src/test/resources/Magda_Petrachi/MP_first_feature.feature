Feature: MP first feature


  Scenario: String length validation
  Given I have a string "OpenAI" MP
  When I check the length of the string MP
  Then the length should be 6 MP

  Scenario: String contains validation
  Given I have a string "OpenAI" MP
  Then the string should contain "AI" MP

  Scenario: Even number validation
  Given I have a number 10 MP
  When I check if the number is even MP
  Then the number should be even MP