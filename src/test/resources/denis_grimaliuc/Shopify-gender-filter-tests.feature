# Created by dgrimaliuc at 07.08.2024
Feature: Gender Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Filtering gender test
    When Select gender "<gender>"
    Then Verify gender is "<gender>"

    Examples:
      | gender |
      | Male   |
      | Female |

