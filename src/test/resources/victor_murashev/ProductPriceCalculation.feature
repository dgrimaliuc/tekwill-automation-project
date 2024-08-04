Feature: Calculate price of products using DataTable

  Scenario: Calculate total price of products
    Given I have the following products:
      | Product Name | Price |
      | Apple        | 2     |
      | Banana       | 1     |
      | Cherry       | 3     |
    When I calculate the total price
    Then the total price should be 6
