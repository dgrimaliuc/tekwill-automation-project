Feature: Cart Tests: checking Plus/Minus functionality

  Background: Open Page
    Given Shopify Page is Open


  Scenario: Click on Plus item several times
    When Collect first item details
    And Add to cart first item
    And Open cart
    And User click on Plus button 2 times
    Then Verify item quantity is "3"
    And Verify price was increased 3 times


  Scenario: Click on Minus item several times
    When Collect first item details
    And Add to cart first item
    And Open cart
    And User click on Plus button 2 times
    And User click on Minus button 2 times
    Then Verify item quantity is "1"
    And Verify price was increased 1 times
    When User click on Minus button 1 times
    Then Verify cart is empty





