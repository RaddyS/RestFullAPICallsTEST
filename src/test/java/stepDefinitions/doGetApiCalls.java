package stepDefinitions;
import aPojo.User;
import cDAO.UserManagement;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;


public class doGetApiCalls {

    private static final ObjectMapper mapper = new ObjectMapper();


    String userID;
    User expectedUser;


    @Given("I have for this GET call a user id")
    public void iHaveForThisGETCallAUserId () throws IOException {
        //TODO: Need to edit the code so that the the user ID is supplied by getParamValueOne();
        //String userID = ParamUtil.getParamValueOne();
        userID = "4145633";
        //need to add assertion to check the user ID is as expected
        System.out.println("The userID to get the user by is: " + userID);
    }


    @When("I send Get request")
    public void iSendAGetRequest () throws IOException, URISyntaxException {
        UserManagement userManagement = new UserManagement();
        expectedUser = userManagement.getUserById(userID);
        String name = expectedUser.getName();
        //need to add assertion to check the request body parts
        System.out.println("The name of the user is: " + name );
    }

    @And("the retrieved user has the same id")
    public void theRetrievedUserHasTheSameId() {
        String actualUserID = expectedUser.getId();
        Assert.assertEquals(actualUserID, userID);

    }


    @Then("the response status code for this GET call should be expectedStatusCode = \"200\"")
    public void theResponseStatusCodeForThisGETCallShouldBeExpectedStatusCode (){
        String actualStatusCode = expectedUser.getStatusCode();
        String expectedStatusCode = "200";
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }


    @Given("I have executed a Get call")
    public void iHaveExecutedAGetCall() throws IOException, URISyntaxException {
        UserManagement userManagement = new UserManagement();
        expectedUser = userManagement.getUserById(userID);
        String statusCode = expectedUser.getStatusCode();
        String expectedStatusCode = "200";
        Assert.assertEquals(expectedStatusCode, statusCode);

    }
    @When("I receive the response body")
    public void iReceiveTheResponseBody() {
        String actualBodyResponse = expectedUser.getResponseBody();
        //need to add assertion to check the request body
        System.out.println("The body response is:" + actualBodyResponse);


    }
    @Then("the response should have expected id, name, email, gender, status")
    public void theResponseShouldHaveExpectedIdNameEmailGenderStatus() {
        String id = expectedUser.getId();
        String name = expectedUser.getName();
        String email = expectedUser.getEmail();
        String gender = expectedUser.getGender();
        String status = expectedUser.getStatus();
        //need to add assertion to check the attributes
        System.out.println("The body parts of user:" + id + name + email + gender + status);

    }


}