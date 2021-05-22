Feature: Automationpractice Newsletter

  Background:
    Given The home page is opened

  Scenario: User send empty email address
    Given User Click news letter subscription button
    Then Newsletter : Invalid email address. error message is shown

  Scenario: User send invalid email address
    Given User enters invalid email
    And User Click news letter subscription button
    Then Newsletter : Invalid email address. error message is shown

  Scenario: User successfully subscribed newsletter
    Given User enters valid email
    And User Click news letter subscription button
    Then Newsletter : You have successfully subscribed to this newsletter.