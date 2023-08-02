package cDAO;

import aPojo.Request;
import aPojo.User;
import bUtilityTest.AccessToken;
import bUtilityTest.ApiConfigUtil;
import bUtilityTest.ParamUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserManagement {

    String BEARER_TOKEN = AccessToken.getInstance().getAccessToken();
    String responseBodyPost;
    String responseBodyPut;
    String responseBodyGet;
    String responseBodyDelete;

    String statusCodePost;
    String statusCodePut;
    String statusCodeGet;
    String statusCodeDelete;

    public UserManagement() throws IOException {
    }

    // TODO: Must decide if I will use this method
    public String performGetRequest() throws URISyntaxException, IOException {

        String url = ApiConfigUtil.getInstance().getEndpointForGet();
        String paramValueOne = ParamUtil.getParamValueOne();
        URI endpointUri = new URIBuilder(url) // set URL
                //.setParameter("id", userID)
                //.setParameter("page", "1") for further development
                //.setParameter("per_page", "20") for further development
                .build();
        HttpGet httpGet = new HttpGet(endpointUri);
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BEARER_TOKEN);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        int integerStatusCodeGet = httpResponse.getStatusLine().getStatusCode();
        statusCodeGet = Integer.toString(integerStatusCodeGet);
        HttpEntity responseEntity = httpResponse.getEntity();
        String responseBodyGet = EntityUtils.toString(responseEntity);
        System.out.println("GET Response status code: " + statusCodeGet);
        System.out.println("GET Response body: " + responseBodyGet);
        httpResponse.close();
        httpClient.close();

        return responseBodyGet + statusCodeGet;

    }
    //TODO: improve the method to work as expected
    public User getUserById(String userID) throws IOException, URISyntaxException {

        String url = ApiConfigUtil.getInstance().getEndpointForGet();
        URI endpointUri = new URIBuilder(url)
                //.setParameter("id", paramValueOne)
                .setParameter("id", userID)
                .build();
        HttpGet httpGet = new HttpGet(endpointUri);
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BEARER_TOKEN);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        int integerStatusCodeGet = httpResponse.getStatusLine().getStatusCode();
        statusCodeGet = Integer.toString(integerStatusCodeGet);
        HttpEntity responseEntity = httpResponse.getEntity();
        responseBodyGet = EntityUtils.toString(responseEntity);
        System.out.println("GET Response status code: " + statusCodeGet);
        System.out.println("GET Response body: " + responseBodyGet);
        httpResponse.close();
        httpClient.close();
        System.out.println("THE DATA ==========>" + responseBodyGet);
        String responseBodyGetWithoutBrackets = responseBodyGet.substring(1, responseBodyGet.length() - 1);
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(responseBodyGetWithoutBrackets, User.class);
        user.setResponseBody(responseBodyGetWithoutBrackets);
        user.setStatusCode(statusCodeGet);
        return user;

    }


    public Request performPostRequest() throws URISyntaxException, IOException {
        String url = ApiConfigUtil.getInstance().getEndpointForPost();
        URI endpointUri = new URIBuilder(url)
                .build();
        ParamUtil.loadFiles();
        String jsonPayload = new String(Files.readAllBytes(Paths.get(ParamUtil.PAYLOAD_FILE_PATH_POST)));
        HttpPost httpPost = new HttpPost(endpointUri);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BEARER_TOKEN);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setEntity(new StringEntity(jsonPayload));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        int integerStatusCodePost = httpResponse.getStatusLine().getStatusCode();
        statusCodePost = Integer.toString(integerStatusCodePost);
        HttpEntity responseEntity = httpResponse.getEntity();
        responseBodyPost = EntityUtils.toString(responseEntity);
        System.out.println("Response status code: " + statusCodePost);
        System.out.println("Response body: " + responseBodyPost);
        httpResponse.close();
        httpClient.close();
        Request request = new Request();
        request.setStatusCode(statusCodePost);
        request.setResponseBody(responseBodyPost);
        // added so that it writes the data to a directory that can be & must be used to load PAYLOAD_FILE_PATH_DELETE
        // it will make the test run smooth by allowing the rest of the method to use the id associated with the posted data
        //TODO: make a method to handle this
        String responseFilePath = "src/test/resourcesTest/PostedData.JSON";
        FileWriter fileWriter = new FileWriter(responseFilePath);
        fileWriter.write(responseBodyPost);
        fileWriter.close();
        return request;
    }



    public Request performPutRequest(String userID) throws URISyntaxException, IOException {
        String url = ApiConfigUtil.getInstance().getEndpointForPut();
        URI endpointUri = new URIBuilder(url + userID)
                .build();
        ParamUtil.loadUpdateFiles();
        HttpPut httpPut = new HttpPut(endpointUri);
        httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BEARER_TOKEN);
        httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String jsonPayload = new String(Files.readAllBytes(Paths.get(ParamUtil.PAYLOAD_FILE_PATH_PUT)));
        httpPut.setEntity(new StringEntity(jsonPayload));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
        int integerStatusCodePut = httpResponse.getStatusLine().getStatusCode();
        statusCodePut = Integer.toString(integerStatusCodePut);
        HttpEntity responseEntity = httpResponse.getEntity();
        responseBodyPut = EntityUtils.toString(responseEntity);
        System.out.println("PUT Response status code: " + statusCodePut);
        System.out.println("PUT Response body: " + responseBodyPut);
        httpResponse.close();
        httpClient.close();
        Request request = new Request();
        request.setStatusCode(statusCodePut);
        request.setResponseBody(responseBodyPut);
        return request;
    }

    public Request performDeleteRequest(String userID) throws IOException, URISyntaxException {
        String url = ApiConfigUtil.getInstance().getEndpointForDelete();
        String paramValueOne = ParamUtil.getParamValueOne();
        //String getParamValueOne = getParamValueOne();
        //endpoint changing constantly must improve
        URI endpointUri = new URIBuilder(url + userID) // set URL
                .build();
        HttpDelete httpDelete = new HttpDelete(endpointUri);
        httpDelete.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BEARER_TOKEN);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
        int integerStatusCodeDelete = httpResponse.getStatusLine().getStatusCode();
        statusCodeDelete = Integer.toString(integerStatusCodeDelete);
        HttpEntity responseEntity = httpResponse.getEntity();
        responseBodyDelete = null;
        if (responseEntity != null) {
            responseBodyDelete = EntityUtils.toString(responseEntity);
        }
        System.out.println("DELETE Response status code: " + statusCodeDelete);
        System.out.println("DELETE Response body: " + responseBodyDelete);
        httpResponse.close();
        httpClient.close();
        Request request = new Request();
        request.setStatusCode(statusCodeDelete);
        request.setResponseBody(responseBodyDelete);
        //added so that it can wipe the data from the file loading this PAYLOAD_FILE_PATH_GET_DELETE
        //it will wipe the file even if the run is not successful
        File fileObj = new File(ParamUtil.PAYLOAD_FILE_PATH_GET_DELETE);
        PrintWriter writer = new PrintWriter(fileObj);
        writer.print("");

        return request;
    }

}
