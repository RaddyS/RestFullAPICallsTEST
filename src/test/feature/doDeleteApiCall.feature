
Feature: Delete User
  Scenario: Delete a user
    Given I have for this Delete call a JSON payload in file <string>
    When I send a Delete request to <string>
    Then the response status code should be expectedStatusCode "204"
