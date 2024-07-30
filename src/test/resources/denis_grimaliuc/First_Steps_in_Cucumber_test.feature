Feature: First Steps in Cucumber

  @Smoke2
  Scenario Outline: Addition of two numbers
    Given I have two numbers "<num1>" and "<num2>"
    When Execute Python Script "{0[0]} + {0[1]}"
    Then Assert that "{1} == <result>"

    Examples:
      | num1 | num2 | result |
      | 5    | 6    | 11     |
      | 8    | 8    | 16     |
      | 9    | 9    | 18     |

