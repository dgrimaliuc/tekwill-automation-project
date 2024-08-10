Feature: Price Filter Test
  Background: Open Page
    Given Shopify Page is Open

  Scenario Outline: Price under $25 or over $100
    When Select price <filter>
    Then Verify price is <filter>

    Examples:
      | filter |
      | "under $25" |
      |"over $100"  |

  Scenario Outline: Price is between
    When Select price between "$<min> and $<max>"
    Then Verify price is between $<min> and $<max>

    Examples:
      | min | max |
      | 25  | 50  |
      | 50  | 100 |

  Scenario: Combine price test
    When Select price "under $25"
    When Select price "over $100"
    Then Verify price is under $25 and over $100