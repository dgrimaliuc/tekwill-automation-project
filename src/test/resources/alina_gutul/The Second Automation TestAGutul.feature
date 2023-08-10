Feature: The Second Automation Test AGutul

 Scenario: String length validation
   Given AG I have a string "Java Help"
   When AG I check the length of the string
   Then AG the length should be "9"

  Scenario: String contains validation
   Given AG I have a string "Java Help"
   Then AG the string should contain "He"

  Scenario:  Even number validation
   Given AG I have a number "10"
   Then AG the number should be even

  Scenario: Odd number validation
   Given AG I have a number "7"
   Then AG the number should be odd

 Scenario: List size validation
  Given AG I have a list with elements
  |Apple|
  |Banana|
  |Orange|
  When AG I check the size of the list
  Then AG the size of the list should be "3"
