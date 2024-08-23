Feature: Cart Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open


  Scenario: Plus item test
    When Collect first item details
    When Add to cart first item
    When Open cart
    When User click on Plus button
    Then Verify item quantity is "2"
    Then Verify price was increased 2 times


  Scenario: Minus item test
    When Collect first item details
    When Add to cart first item
    When Open cart
    When User click on Plus button
    When User click on Minus button
    Then Verify item quantity is "1"
    Then Verify price was increased 1 times

  Scenario: Empty cart test
    When Open cart
    Then Verify cart is empty

  Scenario: Add to cart test
    When Collect first item details
    When Add to cart first item
    When Open cart
    Then Verify cart is not empty

  Scenario: Remove item test
    When Add to cart first item
    When Open cart
    When User click on Plus button 2 times
    When User click on Remove button
    Then Verify cart is empty

  Scenario: Adding 3 times a product inside cart test
    When Collect first item details
    When Add to cart first item
    When Open cart
    When User click on Plus button 2 times
    Then Verify item quantity is "3"
    Then Verify price was increased 3 times

  Scenario: Minus item leads to empty cart test
    When Add to cart first item
    When Open cart
    When User click on Minus button
    Then Verify cart is empty

  Scenario: Click outside cart test
    When Add to cart first item
    When Open cart
    When Add to cart first item
    Then Verify cart is not displayed

  Scenario: Adding Item to cart twice increases quantity of item
    When Add to cart first item
    When Add to cart first item
    When Open cart
    Then Verify item quantity is "2"


  Scenario: Total price test
    When Add random item to cart 4 times
    When Open cart
    When Find sum of all items
    Then Verify total price is correct




