Feature: Tests with Python

  Scenario: String length validation
    Given I have a String "Java Help"
    When  Execute Python Script "len('{0}')"
    Then Assert that "{1} == 9"

  Scenario: String contains validation
    Given I have a String "Java Help"
    Then Assert that "'He' in '{0}'"

  Scenario: Even numbers validation
    Given I have a number "10"
    Then Assert that "{0} % 2 == False"

  Scenario: Odd numbers validation
    Given I have a number "7"
    Then Assert that "{0} % 2 == True"

  Scenario Outline: List Size validation
    Given I have a list with elements "<el1>", "<el2>", "<el3>"
    Then Assert that "len(['{0[0]}','{0[1]}','{0[2]}']) == <result>"
    Examples:
      | el1   | el2    | el3    | result |
      | Apple | Banana | Orange | 3      |