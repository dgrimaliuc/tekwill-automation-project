Feature: Gender Filters Tests


  Background: Open Page
    Given Shopify Page is Open PM3

  Scenario Outline: Filtering gender test
    When Select gender "<gender>"
    Then Verify gender is "<gender>"

    Examples:
      | gender |
      | Male   |
      | Female |

  Scenario: Combine gender filters test
    When Select gender "Male"
    When Select gender "Female"
    Then Verify gender is Male and Female