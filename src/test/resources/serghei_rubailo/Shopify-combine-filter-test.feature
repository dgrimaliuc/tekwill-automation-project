Feature: Price filter test
  Background: Open Page
    Given Shopify Page is Open

  Scenario: Nothing to show
    When Select price "under $25"
    When Select gender "Male"
    When Select size "S"
    Then The message "Nothing to show" is displayed