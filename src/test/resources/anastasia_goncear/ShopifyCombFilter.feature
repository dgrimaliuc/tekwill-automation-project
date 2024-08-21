Feature: Price Filters Tests
  # Enter feature description here

  Background: Open Page
    Given Shopify Page is Open AN

  Scenario: Black color test
    When Select a price "Under $25"
    When Select  color "Red"
    When Select   gender "Male"
    Then The Message is "Nothing to show" is displayed
