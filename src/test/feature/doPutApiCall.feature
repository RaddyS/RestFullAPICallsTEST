
Feature: Edit User
  Scenario: PUT to user
    Given I have for this PUT call JSON payload in file <string>
    When I send a PUT request to <string>
    Then the response code should be expectedStatusCode = "200"
