Feature: First Steps in Cucumber

  Background: Before All
    Given Echo Background

  @Smoke
  Scenario: Addition of two numbers
    Given I have two numbers "5" and "6"
    When I add the numbers
    Then the result should be "11"
