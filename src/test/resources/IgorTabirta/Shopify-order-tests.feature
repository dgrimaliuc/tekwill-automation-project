
Feature: Cart Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open


  Scenario: Order test
    When Add to cart first item
    When Open cart
    When User click on Order button
    Then Verify order is successful
    When Open cart
    Then Verify cart is empty





