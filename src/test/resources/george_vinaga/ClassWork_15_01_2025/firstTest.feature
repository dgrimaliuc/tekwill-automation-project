#Created by gVinaga at 15.01.2025
 Feature: My first test
  # Feature description here

  Scenario: Validarea că „Hello World” conține „Hello”
    Given I have a string "Hello World"
    When I check if it contains "Hello"
    Then It should return true