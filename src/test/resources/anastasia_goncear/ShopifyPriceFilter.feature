
Feature: Price Filters Tests


  Background: Open Page
    Given Shopify Page is Open AN

  Scenario Outline: Price is under 25 or over 100
    When Select the price "<filter>"
    Then Verify the price is "<filter>"

    Examples:
      | filter    |
      | Under $25 |
      | Over $100 |

  Scenario Outline:  Price is between min and max
    When Select prices between "$<min> to $<max>"
    Then Check prices is between $<min> and $<max>

    Examples:
      | min | max |
      | 25  | 50  |
      | 50  | 100 |


