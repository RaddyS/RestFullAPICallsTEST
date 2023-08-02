Feature: GET User

  Scenario: Get API call response code
    Given I have for this GET call a user id
    When I send Get request
    And the retrieved user has the same id
    Then the response status code for this GET call should be expectedStatusCode = "200"

  Scenario:
    Given I have executed a Get call
    When I receive the response body
    Then the response should have expected id, name, email, gender, status


