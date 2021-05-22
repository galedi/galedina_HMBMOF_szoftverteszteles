Feature: AutomationPractice Search

  Background:
    Given The home page is opened

    Scenario: User click search button empty text
    Given The search button is clicked
    Then Please enter search keyword message is shown

    Scenario Outline:
      Given The user enters the '<search_string>' in '<field>'
      And The search button is clicked
      Then The '<msg>' is shown
      Examples:
        | search_string | field            | msg                        |
        |               | search_query_top | 0 results have been found. |
        | Sleeve        | search_query_top | 4 results have been found. |
        | T-shirts      | search_query_top | 1 result has been found.   |
        | Summer Dress  | search_query_top | 2 results have been found. |
        | Chiffon Dress | search_query_top | 2 results have been found. |