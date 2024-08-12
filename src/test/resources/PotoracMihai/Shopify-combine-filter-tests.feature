Feature: Price Filters Tests


  Background: Open Page
    Given Shopify Page is Open

  Scenario: Black color test
    When Select price "Under $25"
    When Select color "Red"
    When Select gender "Male"
    Then The Message "Nothing to show" is displayed