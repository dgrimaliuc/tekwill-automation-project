# Created by dgrimaliuc at 07.08.2024
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




