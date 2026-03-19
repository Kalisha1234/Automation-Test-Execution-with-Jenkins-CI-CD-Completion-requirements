package org.example.carts;

import io.restassured.http.ContentType;
import org.example.base.BaseTest;
import org.example.testdata.TestData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CartsTest extends BaseTest {

    @Test(priority = 1)
    public void testGetAllCarts() {
        given()
            .when()
            .get("/carts")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 2)
    public void testGetSingleCart() {
        given()
            .pathParam("id", TestData.VALID_CART_ID)
            .when()
            .get("/carts/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(TestData.VALID_CART_ID));
    }

    @Test(priority = 3)
    public void testCreateCart() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.CREATE_CART_JSON)
            .when()
            .post("/carts")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test(priority = 4)
    public void testUpdateCart() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", TestData.VALID_CART_ID)
            .body(TestData.UPDATE_CART_JSON)
            .when()
            .put("/carts/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 5)
    public void testDeleteCart() {
        given()
            .pathParam("id", TestData.VALID_CART_ID)
            .when()
            .delete("/carts/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 6)
    public void testGetUserCarts() {
        given()
            .pathParam("userId", TestData.VALID_USER_ID)
            .when()
            .get("/carts/user/{userId}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test(priority = 7)
    public void testGetCartsWithDateRange() {
        given()
            .queryParam("startdate", TestData.CART_START_DATE)
            .queryParam("enddate", TestData.CART_END_DATE)
            .when()
            .get("/carts")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test(priority = 8)
    public void testCartResponseSchema() {
        given()
            .pathParam("id", TestData.VALID_CART_ID)
            .when()
            .get("/carts/{id}")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("userId", notNullValue())
            .body("products", notNullValue());
    }

    @Test(priority = 9)
    public void testCreateCartWithInvalidData() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.INVALID_CART_JSON)
            .when()
            .post("/carts")
            .then()
            .statusCode(anyOf(equalTo(200), equalTo(201), equalTo(400)));
    }
}
