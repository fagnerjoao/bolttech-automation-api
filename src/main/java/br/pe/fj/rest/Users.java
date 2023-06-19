package br.pe.fj.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class Users {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }


    @Test
    public void test_createAccount_1() {
        String responseBody = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("name", "Fulano Silva")
                .formParam("email", "fulano.silva@uorak.com")
                .formParam("password", "password123")
                .formParam("title", "Mr")
                .formParam("birth_date", "10")
                .formParam("birth_month", "01")
                .formParam("birth_year", "1990")
                .formParam("firstname", "John")
                .formParam("lastname", "Doe")
                .formParam("company", "Example Company")
                .formParam("address1", "123 Main St")
                .formParam("address2", "Apt 4B")
                .formParam("country", "United States")
                .formParam("zipcode", "12345")
                .formParam("state", "New York")
                .formParam("city", "New York City")
                .formParam("mobile_number", "1234567890")
        .when()
                .post("/createAccount")
        .then()
                .log().all()
                .statusCode(200)
                .extract().response().getBody().asString()
        ;

        JSONObject json = new JSONObject(responseBody);

        int responseCode = json.getInt("responseCode");
        Object messageObj = json.get("message");

        if (messageObj instanceof JSONArray) {
            JSONArray messageInfo = (JSONArray) messageObj;
            assertThat(responseCode, equalTo(201));
            assertThat(messageInfo, hasItem("User created!"));
        } else if (messageObj instanceof String) {
            String message = (String) messageObj;
            assertThat(responseCode, equalTo(201));
            assertThat(message, equalTo("User created!"));
        } else {
            throw new IllegalStateException("Unexpected type for 'message' field: " + messageObj.getClass());
        }
    }

    @Test
    public void test_getUserDetailByEmail_2() {
        Response getUserResponse =  given()
                .log().all()
                .contentType(ContentType.JSON)
                .formParam("email", "fulano.silva@uorak.com")
        .when()
                .get("/getUserDetailByEmail")
        .then()
                .log().all()
                .statusCode(200)
                .extract().response()
        ;

        JSONObject getUserResponseJson = new JSONObject(getUserResponse.getBody().asString());
        int getUserResponseCode = getUserResponseJson.getInt("responseCode");
        JSONObject user = getUserResponseJson.getJSONObject("user");

        assertThat(getUserResponseCode, equalTo(200));
       //assertThat(user.getInt("id"), equalTo(105057));
        assertThat(user.getString("name"), equalTo("Fulano Silva"));
        assertThat(user.getString("email"), equalTo("fulano.silva@uorak.com"));
    }

    @Test
    public void test_updateAccount3() {
        Response response = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("name", "Fulano Silva")
                .formParam("email", "fulano.silva@uorak.com")
                .formParam("password", "password123")
                .formParam("birth_day", 10)
        .when()
                .put("/updateAccount")
        .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        int responseCode = jsonResponse.getInt("responseCode");
        String message = jsonResponse.getString("message");

        assertThat(responseCode, equalTo(200));
        assertThat(message, equalTo("User updated!"));
    }

    @Test
    public void test_deleteAccount_4() {
        Response response = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "fulano.silva@uorak.com")
                .formParam("password", "password123")
        .when()
                .delete("/deleteAccount");
        response.then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.HTML)
        ;

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String message = jsonPath.getString("message");

        assertThat(message, is("Account deleted!"));
    }

}
