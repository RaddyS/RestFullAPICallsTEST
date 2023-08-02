package stepDefinitions;

import aPojo.Request;
import cDAO.UserManagement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static bUtilityTest.ParamUtil.*;

// TODO: redo the whole class

public class doPutApiCalls {

    Request requestedPut;
    String jsonPayload;
    String userID;


    @Given("I have for this PUT call JSON payload in file <string>")
    public void iHaveForThisPUTCallJSONPayloadInFileString() throws IOException{
        loadUpdateFiles();
        jsonPayload = new String(Files.readAllBytes(Paths.get(PAYLOAD_FILE_PATH_PUT)));
        Assert.assertNotNull("JSON Payload should not be null", jsonPayload);

    }

    @When("I send a PUT request to <string>")
    public void iSendAPUTRequestToString() throws IOException, URISyntaxException {
        UserManagement userManagement = new UserManagement();
        userID = importUserID();
        requestedPut = userManagement.performPutRequest(userID);
        String actualRequestBody = requestedPut.getResponseBodyPost();
        // Extract assertion data
        //TODO: make into a method add to utility class
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(actualRequestBody);
        String actualRequestBodyUserID = responseJson.get("id").asText();
        String actualRequestBodyUserName = responseJson.get("name").asText();
        String actualRequestBodyUserEmail = responseJson.get("email").asText();
        String actualRequestBodyUserGender = responseJson.get("gender").asText();
        String actualRequestBodyUserStatus = responseJson.get("status").asText();

        JsonNode responseJsonPayLoad = objectMapper.readTree(jsonPayload);
        String jsonPayloadUserName = responseJsonPayLoad.get("name").asText();
        String jsonPayloadUserEmail = responseJsonPayLoad.get("email").asText();
        String jsonPayloadUserGender = responseJsonPayLoad.get("gender").asText();
        String jsonPayloadUserStatus = responseJsonPayLoad.get("status").asText();


        Assert.assertEquals(actualRequestBodyUserName, jsonPayloadUserName);
        Assert.assertEquals(actualRequestBodyUserEmail , jsonPayloadUserEmail);
        Assert.assertEquals(actualRequestBodyUserGender, jsonPayloadUserGender);
        Assert.assertEquals(actualRequestBodyUserStatus, jsonPayloadUserStatus);

    }



    @Then("the response code should be expectedStatusCode = \"200\"")
    public void theResponseStatusCodeShouldBeShouldBeExpectedStatusCode() {
        String actualStatusCode = requestedPut.getStatusCodePost();
        String expectedStatusCode = "200";
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

}