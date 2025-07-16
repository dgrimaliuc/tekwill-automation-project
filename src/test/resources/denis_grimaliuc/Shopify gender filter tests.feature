# Created by dgrimaliuc at 07.07.2025
Feature: Shopify gender filter tests

  Background: Open the Shopify page
    Given I open the Shopify page

  Scenario: Male gender filter test
    When I select Male filter
    Then I should see products with "Male" gender

  Scenario: Female gender filter test
    When I select Female filter
    Then I should see products with "Female" gender

  Scenario: Female and Male gender filter test
    When I select Female filter
    When I select Male filter
    Then I should see products with "Female" and "Male" genders
