package aPojo;

/**
 * data holding classes, parameters and constructors
 * plane old java object
 */

public class User {
    String id;
    String name;
    String email;
    String gender;
    String status;
    String statusCode;
    String responseBody;


    //default constructor
    public User(){

    }



    public User(String id, String name, String email, String gender, String status, String statusCode, String responseBody ){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
        this.statusCode = statusCode;
        this.responseBody = responseBody;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
