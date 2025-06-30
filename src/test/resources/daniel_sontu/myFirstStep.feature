Feature: My first feature file

  Scenario: Addition of two numbers
    Given I have two numbers 5 and 3
    When I add the numbers
    Then the result should be 8


  Scenario: Subtraction of two numbers
      Given I have two numbers 12 and 8
      When I subtract the number
      Then the result should be 4

    Scenario: Concatenation of two strings
      Given I have two strings "Hello" and "World"
      When I concatenate the strings
      Then the result should be "HelloWorld"

      Scenario: String length validation
        Given I have a string "OpenAI"
        When i check the length of the string
        Then the length should be 6

        Scenario: String contains validation
          Given I have a string "OpenAI"
          Then I check if the string contains "AI"
