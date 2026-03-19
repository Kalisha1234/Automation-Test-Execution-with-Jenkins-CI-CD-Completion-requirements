package org.example.products;

import io.restassured.http.ContentType;
import org.example.base.BaseTest;
import org.example.testdata.TestData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductsTest extends BaseTest {

    @Test(priority = 1)
    public void testGetAllProducts() {
        given()
            .when()
            .get("/products")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 2)
    public void testGetSingleProduct() {
        given()
            .pathParam("id", TestData.VALID_PRODUCT_ID)
            .when()
            .get("/products/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(TestData.VALID_PRODUCT_ID))
            .body("title", notNullValue());
    }

    @Test(priority = 3)
    public void testGetAllCategories() {
        given()
            .when()
            .get("/products/categories")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 4)
    public void testGetProductsByCategory() {
        given()
            .pathParam("category", TestData.PRODUCT_CATEGORY)
            .when()
            .get("/products/category/{category}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 5)
    public void testCreateProduct() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.CREATE_PRODUCT_JSON)
            .when()
            .post("/products")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    @Test(priority = 6)
    public void testUpdateProduct() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", TestData.VALID_PRODUCT_ID)
            .body(TestData.UPDATE_PRODUCT_JSON)
            .when()
            .put("/products/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 7)
    public void testDeleteProduct() {
        given()
            .pathParam("id", TestData.VALID_PRODUCT_ID)
            .when()
            .delete("/products/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 8)
    public void testGetInvalidProduct() {
        given()
            .pathParam("id", TestData.INVALID_PRODUCT_ID)
            .when()
            .get("/products/{id}")
            .then()
            .statusCode(200);
    }

    @Test(priority = 9)
    public void testGetLimitedProducts() {
        given()
            .queryParam("limit", TestData.PRODUCT_LIMIT)
            .when()
            .get("/products")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", lessThanOrEqualTo(TestData.PRODUCT_LIMIT));
    }

    @Test(priority = 10)
    public void testGetSortedProducts() {
        given()
            .queryParam("sort", TestData.PRODUCT_SORT_ORDER)
            .when()
            .get("/products")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 11)
    public void testProductResponseSchema() {
        given()
            .pathParam("id", TestData.VALID_PRODUCT_ID)
            .when()
            .get("/products/{id}")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("title", notNullValue())
            .body("price", notNullValue())
            .body("description", notNullValue())
            .body("category", notNullValue())
            .body("image", notNullValue());
    }

    @Test(priority = 12)
    public void testCreateProductWithInvalidData() {
        given()
            .contentType(ContentType.JSON)
            .body(TestData.INVALID_PRODUCT_JSON)
            .when()
            .post("/products")
            .then()
            .statusCode(anyOf(equalTo(200), equalTo(201), equalTo(400)));
    }
}
