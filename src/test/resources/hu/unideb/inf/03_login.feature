Feature: AutomationPractice Login

  Background:
    Given The home page is opened
    And The Sign In link is clicked

  Scenario Outline:
    Given the '<field>' is filled with '<value>'
    When the Sign In button is clicked
    Then the '<msg>' error message is shown
    Examples:
      | field | value             | msg                        |
      | email |                   | An email address required. |
      | email | invalid.email.com | Invalid email address.     |
      | email | valid@email.com   | Password is required.      |

  Scenario Outline:
    Given the '<field>' is filled with '<value>'
    Then the '<pwField>' is filled with '<pwValue>'
    When the Sign In button is clicked
    Then the '<pwMsg>' error message is shown
    Examples:
      | field | value           | pwField | pwValue       | pwMsg                  |
      | email | valid@email.com | passwd  | something     | Authentication failed. |
      | email | valid@email.com | passwd  | somethingElse | Authentication failed. |

  Scenario: User enter invalid email
    Given Create account is clicked
    Then an Invalid email address error message is show
    Then The email address field is field a valid email
    Then I click Create an account