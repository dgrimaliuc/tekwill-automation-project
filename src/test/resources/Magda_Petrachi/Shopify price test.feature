Feature: Shopify test

  Scenario: Open Shopify page
    Given I open Shopify page
    Then I should see the Shopify page
    Then I find the card button
    Then I find the filter section


    Scenario: I check filter under $25
      Given I open Shopify page
      When select filter under 25
      Then Shoude find the products which have price under 25


    Scenario: I check filter 20 to 50
      Given I open Shopify page
      When select filter between 20 and 50
      Then Shoude find the products which have prices between 20 and 50


    Scenario: I check filter 50 to 100
      Given I open Shopify page
      When select filter between 50 and 100
      Then Shoude find the products which have prices between 50 and 100


    Scenario: I check filter over $100
      Given I open Shopify page
      When select filter over 100
      Then Shoude find the products which have price over 100
      


