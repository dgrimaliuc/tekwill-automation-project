Feature: Second Auto HW 2

 Scenario: String length validation

 Given DT I have a string "Java Help"
   When DT I check the length of the string
   Then DT the length should be "9"

  Scenario: String contains validation
   Given DT I have a string "Java Help"
   Then DT the string should contain "He"

  Scenario:  Even number validation
   Given DT I have a number "10"
   Then DT the number should be even

  Scenario: Odd number validation
   Given DT I have a number "7"
   Then DT the number should be odd

 Scenario: List size validation
  Given DT I have a list with elements
  |Apple|
  |Banana|
  |Orange|
  When DT I check the size of the list
  Then DT the size of the list should be "3"
