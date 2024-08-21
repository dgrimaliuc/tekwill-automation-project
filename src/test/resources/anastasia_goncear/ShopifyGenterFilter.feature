Feature: Genders Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open AN

  Scenario Outline: Filtering gender test
    When Select a gender "<gender>"
    Then Verify the gender is "<gender>"

    Examples:
      | gender |
      | Male   |
      | Female |