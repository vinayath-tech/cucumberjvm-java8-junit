Feature: Login functionality
  As a customer
  I need to be able to login to my site
  So that I can shop securely

  Scenario: valid user login
    Given I am on login page
    When I login with valid user credentials
    Then I should be able to successfully login