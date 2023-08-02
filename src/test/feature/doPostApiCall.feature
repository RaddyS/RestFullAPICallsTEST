
Feature: User registration
  Scenario: Create a new user
    Given I have a User registration JSON payload in file <string>
    When I send a POST request to <string>
    Then the response status code should be expectedStatusCode "201"


