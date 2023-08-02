package aPojo;

/**
 * data holding classes, parameters and constructors
 * plane old java object
 */


public class Request {
    String responseBody;
    String statusCode;



    public Request(){
    }

    public Request(String responseBody, String statusCode){
        this.statusCode = statusCode;
        this.responseBody = responseBody;


    }

    public String getResponseBodyPost() {
        return responseBody;
    }
    public String getStatusCodePost() {
        return statusCode;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Request{" +
                "userStatus='" + responseBody+ '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
