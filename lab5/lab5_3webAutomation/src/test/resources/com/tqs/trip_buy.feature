@trip_sample
Feature: Buy trip in BlazeDemo
  To allow a customer to find and buy a trip in the BlazeDemo website

  Background:
    Given the customer is at "https://blazedemo.com"

  Scenario: Search for and obtain a valid trip
    Given the customer is on the "BlazeDemo" page
    When the customer selects the origin as "Mexico City"
    And the customer selects the destination as "London"
    And the customer clicks on the "Find Flights" button
    Then the customer should be redirected to the "reserve" page
    And should see a list of flights from "Mexico City" to "London"
    Given the customer is on the "reserve" page
    When the customer clicks on the "Choose This Flight" button
    Then the customer should be redirected to the "purchase" page
    Given the customer is on the "purchase" page
    When the customer fills out the form with valid data
    And the customer clicks on the "Purchase Flight" button
    Then the customer should be redirected to the "confirmation" page

  Scenario: Search for trip and obtain wrong cost
    Given the customer is on the "BlazeDemo" page
    When the customer selects the origin as "Mexico City"
    And the customer selects the destination as "London"
    And the customer clicks on the "Find Flights" button
    Then the customer should be redirected to the "reserve" page
    And should see a list of flights from "Mexico City" to "London"
    Given the customer is on the "reserve" page
    When the customer clicks on the "Choose This Flight" button
    Then the customer should be redirected to the "purchase" page
    And should see the wrong cost for the trip