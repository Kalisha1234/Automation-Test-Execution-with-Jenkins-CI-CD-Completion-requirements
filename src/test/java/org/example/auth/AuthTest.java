package org.example.auth;

import io.restassured.http.ContentType;
import org.example.base.BaseTest;
import org.example.testdata.TestData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_VALID_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body("token", notNullValue());
    }

    @Test(priority = 2)
    public void testLoginWithInvalidCredentials() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_INVALID_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(401);
    }

    @Test(priority = 3)
    public void testLoginWithMissingUsername() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_MISSING_USERNAME_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(400);
    }

    @Test(priority = 4)
    public void testLoginWithMissingPassword() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_MISSING_PASSWORD_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(400);
    }

    @Test(priority = 5)
    public void testLoginWithEmptyBody() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_EMPTY_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(400);
    }

    @Test(priority = 6)
    public void testLoginResponseSchema() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.LOGIN_VALID_JSON)
            .when()
            .post("/auth/login")
            .then()
            .statusCode(201)
            .body("token", notNullValue())
            .body("token", not(emptyString()));
    }
}
