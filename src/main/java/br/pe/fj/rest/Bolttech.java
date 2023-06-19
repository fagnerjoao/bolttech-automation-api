package br.pe.fj.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Bolttech {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://192.168.1.199:5000/api";
    }



    @Test
    public void registerUser() {
        String responseBody = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "mariaeduarda22@uorak.com")
                .formParam("password", "password123")
                .formParam("role", "write")
                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response().getBody().asString()
                ;

        JSONObject json = new JSONObject(responseBody);

        boolean success = json.getBoolean("success");
        String message = json.getString("message");

        assertThat(success, equalTo(true));
        assertThat(message, equalTo("created with role write"));
    }

@Test
    public void login() {
        String responseBody = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("Username", "mariaeduarda22@uorak.com")
                .formParam("Password", "password123")
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(200)
                .extract().response().getBody().asString()
                ;
    }
}
