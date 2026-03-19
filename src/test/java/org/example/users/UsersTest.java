package org.example.users;

import io.restassured.http.ContentType;
import org.example.base.BaseTest;
import org.example.testdata.TestData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

    @Test(priority = 1)
    public void testGetAllUsers() {
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 2)
    public void testGetSingleUser() {
        given()
            .pathParam("id", TestData.VALID_USER_ID)
            .when()
            .get("/users/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(TestData.VALID_USER_ID))
            .body("username", notNullValue());
    }

    @Test(priority = 3)
    public void testCreateUser() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.CREATE_USER_JSON)
            .when()
            .post("/users")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test(priority = 4)
    public void testUpdateUser() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", TestData.VALID_USER_ID)
            .body(TestData.UPDATE_USER_JSON)
            .when()
            .put("/users/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 5)
    public void testDeleteUser() {
        given()
            .pathParam("id", TestData.VALID_USER_ID)
            .when()
            .delete("/users/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 6)
    public void testGetLimitedUsers() {
        given()
            .queryParam("limit", TestData.USER_LIMIT)
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", lessThanOrEqualTo(TestData.USER_LIMIT));
    }

    @Test(priority = 7)
    public void testGetSortedUsers() {
        given()
            .queryParam("sort", TestData.SORT_ORDER)
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test(priority = 8)
    public void testUserResponseSchema() {
        given()
            .pathParam("id", TestData.VALID_USER_ID)
            .when()
            .get("/users/{id}")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("email", notNullValue())
            .body("username", notNullValue())
            .body("name", notNullValue())
            .body("address", notNullValue())
            .body("phone", notNullValue());
    }

    @Test(priority = 9)
    public void testCreateUserWithInvalidData() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.INVALID_USER_JSON)
            .when()
            .post("/users")
            .then()
            .statusCode(anyOf(equalTo(200), equalTo(201), equalTo(400)));
    }

    @Test(priority = 10)
    public void testGetInvalidUser() {
        given()
            .pathParam("id", TestData.INVALID_PRODUCT_ID)
            .when()
            .get("/users/{id}")
            .then()
            .statusCode(200);
    }
}
