Feature: Automationpractice Shopping cart
  Background:
    Given The home page is opened

  Scenario: User adds an item to the cart
    Given Item has been added to the cart
    When The cart button is clicked
    Then The cart page loads
    And One product is displayed in a row in the cart
    And Reset

  Scenario: User removes item to shopping cart
    Given Item has been added to the cart
    When The cart button is clicked
    Then The cart page loads
    When The item remove button is pressed
    Then The cart should be empty
    And Reset

