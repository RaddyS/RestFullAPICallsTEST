package stepDefinitions;
import aPojo.Request;
import cDAO.UserManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

import static bUtilityTest.ParamUtil.getParamValueDelete;


// TODO: redo the whole class

public class doDeleteApiCalls {

    Request requestedDelete;
    String userID;

    @Given("I have for this Delete call a JSON payload in file <string>")
    public void iHaveForThisDeleteCallAJSONPayloadInFileString() throws IOException {
        userID = getParamValueDelete();
    }

        @When("I send a Delete request to <string>")
    public void iSendADeleteRequestToString() throws IOException, URISyntaxException {
            UserManagement userManagement = new UserManagement();
            requestedDelete = userManagement.performDeleteRequest(userID);
            String actualResponseBody = requestedDelete.getResponseBodyPost();
            System.out.println(actualResponseBody);

    }


    @Then("the response status code should be expectedStatusCode \"204\"")
    public void theResponseStatusCodeForDeleteShouldBeExpectedStatusCode() {
        //204 is acceptable code when Delete call is executed successfully
        String actualStatusCode = requestedDelete.getStatusCodePost();
        String expectedStatusCode = "204";
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }



}

