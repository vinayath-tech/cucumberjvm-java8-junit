Feature: Search for a dress

  Scenario: Sort by lowest rate on search
    Given I am on the home page
    When I search for "Chiffon Dress"
    Then I should see 2 dress on results
    And the dress should be sorted by lowest price