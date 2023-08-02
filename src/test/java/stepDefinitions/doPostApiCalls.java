package stepDefinitions;

import aPojo.Request;
import cDAO.UserManagement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static bUtilityTest.ParamUtil.*;

public class doPostApiCalls {

    Request requestedPost;
    String jsonPayload;

    @Given("I have a User registration JSON payload in file <string>")
    public void iHaveAUserRegistrationJSONPayloadInFileString() throws IOException {
        loadFiles();
        jsonPayload = new String(Files.readAllBytes(Paths.get(PAYLOAD_FILE_PATH_POST)));
        Assert.assertNotNull("JSON Payload should not be null", jsonPayload);
    }

    @When("I send a POST request to <string>")
    public void iSendAPOSTRequestToString() throws URISyntaxException, IOException {
        UserManagement userManagement = new UserManagement();
        requestedPost = userManagement.performPostRequest();
        String actualRequestBody = requestedPost.getResponseBodyPost();
        // Remove the ID from the actualRequestBody
        //TODO: make into a method add to utility class
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(actualRequestBody);
        //String userID = responseJson.get("id").asText();
        exportUserID(responseJson);
        ((ObjectNode) responseJson).remove("id");
        String modifiedJsonString = objectMapper.writeValueAsString(responseJson);

        Assert.assertEquals(modifiedJsonString, jsonPayload);
        System.out.println("this is RequestBody without ID :" + modifiedJsonString + "this is the jsonPayload:" + jsonPayload);

    }

    @Then("the response status code should be expectedStatusCode \"201\"")
    public void theResponseStatusCodeShouldBeExpectedStatusCode() {
        //201 is acceptable code when a Post call is executed successfully
        String actualStatusCode = requestedPost.getStatusCodePost();
        String expectedStatusCode = "201";
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

}
