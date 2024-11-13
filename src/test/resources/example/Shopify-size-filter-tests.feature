# Created by dgrimaliuc at 07.08.2024
Feature: Price Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Black color test
    When Select size "<size>"
    Then Verify size is "<size>"

    Examples:
      | size |
      | S    |
      | M    |
      | L    |
      | XL   |
