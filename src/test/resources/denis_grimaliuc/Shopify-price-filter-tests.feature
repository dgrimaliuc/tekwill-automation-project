# Created by dgrimaliuc at 07.08.2024
Feature: Price Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Price is under 25 or over 100
    When Select price "<filter>"
    Then Verify price is "<filter>"

    Examples:
      | filter    |
      | Under $25 |
      | Over $100 |


  Scenario Outline:  Price is between min and max
    When Select price between "$<min> to $<max>"
    Then Verify price is between $<min> and $<max>

    Examples:
      | min | max |
      | 25  | 50  |
      | 50  | 100 |

  Scenario: Combine price filters test
    When Select price "Under $25"
    When Select price "Over $100"
    Then Verify price is Under $25 and Over $100


