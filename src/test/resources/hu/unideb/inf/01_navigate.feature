Feature: AutomationPractice Navigate buttons

  Background:
    Given The home page is opened


    Scenario Outline:
      When the '<button>' link is clicked
      Then the '<url>' is loaded
      Examples:
        | button       | url                                                             |
        | Specials     | http://automationpractice.com/index.php?controller=prices-drop  |
        | New products | http://automationpractice.com/index.php?controller=new-products |
        | Best sellers | http://automationpractice.com/index.php?controller=best-sales   |
        | Our stores   | http://automationpractice.com/index.php?controller=stores       |