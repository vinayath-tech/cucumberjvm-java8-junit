Feature: Display dresses
  As a customer
  I should be able to see the list of new dresses available
  So that I can choose the latest collection to buy

  Background:
    Given I navigate to the dress categories

  Scenario: Display list of new dresses
    Then I should see the list of latest collections

  Scenario: Dress should be advertised with color options
    When I view the "Printed Chiffon Dress"
    Then the dress should be available in following colors:
        | Green  |
        | Yellow |

  Scenario: Show images based on color selected
    Given I am on the detail page of the dress
    When I select "Yellow" color
    Then I should see 2 dresses available with yellow color

  Scenario: Should have summer dress in stock
    Then I should see atleast 2 Printed Summer Dress on popular section

  @wip
  Scenario: Add dresses less than 20 pounds to shopping cart
    When I choose dress less than 20 pounds
    Then a total of 1 dress should be added to the cart