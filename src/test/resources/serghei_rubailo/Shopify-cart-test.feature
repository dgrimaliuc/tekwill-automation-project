Feature: Cart Tests

  Background: Open Page
    Given Shopify Page is Open


  Scenario: Add to cart test
    When Collect first item details
    When Add the item to cart
    When Open cart
    Then Verify that cart is not empty

  Scenario: Plus item test
    When Collect first item details
    When Add the item to cart
    When Open cart
    When User clicks on Plus button
    Then Verify item quantity is "2"
    Then Verify price is changed 2 times

  Scenario: Minus item test
    When Collect first item details
    When Add the item to cart
    When Open cart
    When User clicks on Plus button
    When User clicks on Minus button
    Then Verify item quantity is "1"
    Then Verify price is changed 1 times

  Scenario: Empty cart test
    When Open cart
    Then Verify "Your cart is empty" message is shown