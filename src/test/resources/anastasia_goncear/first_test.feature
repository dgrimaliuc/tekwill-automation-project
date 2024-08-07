Feature: Hello World Feature

  Scenario Outline: Check for Hello
    Given I have a Ustring <string>
    Then The string should Ucontain <subString>
    Examples:
      | string      | subString |
      | "Java Help" | "Java"      |
