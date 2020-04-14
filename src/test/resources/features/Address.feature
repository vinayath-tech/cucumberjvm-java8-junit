Feature: Update customer address
  As a regular customer
  I need to be able to update my address details
  So that my deliveries are not lost when I move my address

  Scenario: Add new address
    Given I am logged into the application
    And I navigate to add new address form
    Then I fill in the form using the following data:
      | address   | city | state     | pocode | number     |
      |  dartford | kent | Alabama   |  55416 | 111222333  |
    Then I should be able to successfully submit my details
