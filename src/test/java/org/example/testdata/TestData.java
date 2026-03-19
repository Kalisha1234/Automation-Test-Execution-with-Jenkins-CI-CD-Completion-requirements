package org.example.testdata;

public class TestData {
    
    // Product Test Data
    public static final String PRODUCT_CATEGORY = "electronics";
    public static final int VALID_PRODUCT_ID = 1;
    public static final int INVALID_PRODUCT_ID = 99999;
    public static final int PRODUCT_LIMIT = 5;
    public static final String PRODUCT_SORT_ORDER = "desc";
    
    // Cart Test Data
    public static final int VALID_CART_ID = 1;
    public static final int VALID_USER_ID = 1;
    public static final String CART_START_DATE = "2020-01-01";
    public static final String CART_END_DATE = "2020-12-31";
    
    // User Test Data
    public static final String USER_EMAIL = "john@example.com";
    public static final String USER_USERNAME = "johndoe";
    public static final String USER_PASSWORD = "pass123";
    public static final String UPDATED_EMAIL = "john.updated@example.com";
    public static final String UPDATED_USERNAME = "johndoe_updated";
    public static final int USER_LIMIT = 5;
    public static final String SORT_ORDER = "desc";
    
    // Auth Test Data
    public static final String VALID_USERNAME = "mor_2314";
    public static final String VALID_PASSWORD = "83r5^_";
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "wrong_password";
    
    // JSON Payloads - Products
    public static final String CREATE_PRODUCT_JSON = "{\"title\":\"test product\",\"price\":13.5,\"category\":\"electronics\"}";
    public static final String UPDATE_PRODUCT_JSON = "{\"title\":\"updated product\",\"price\":20.0}";
    public static final String INVALID_PRODUCT_JSON = "{\"title\":\"\",\"price\":-10}";
    public static final String MISSING_REQUIRED_FIELD_JSON = "{\"price\":13.5}";
    
    // JSON Payloads - Carts
    public static final String CREATE_CART_JSON = "{\"userId\":1,\"products\":[{\"productId\":1,\"quantity\":2}]}";
    public static final String UPDATE_CART_JSON = "{\"userId\":1,\"products\":[{\"productId\":2,\"quantity\":3}]}";
    public static final String INVALID_CART_JSON = "{\"userId\":-1,\"products\":[]}";
    
    // JSON Payloads - Users
    public static final String CREATE_USER_JSON = "{\"email\":\"john@example.com\",\"username\":\"johndoe\",\"password\":\"pass123\"}";
    public static final String UPDATE_USER_JSON = "{\"email\":\"john.updated@example.com\",\"username\":\"johndoe_updated\"}";
    public static final String INVALID_USER_JSON = "{\"email\":\"invalid-email\",\"username\":\"\"}";
    
    // JSON Payloads - Auth
    public static final String LOGIN_VALID_JSON = "{\"username\":\"mor_2314\",\"password\":\"83r5^_\"}";
    public static final String LOGIN_INVALID_JSON = "{\"username\":\"invalid_user\",\"password\":\"wrong_password\"}";
    public static final String LOGIN_MISSING_USERNAME_JSON = "{\"password\":\"83r5^_\"}";
    public static final String LOGIN_MISSING_PASSWORD_JSON = "{\"username\":\"mor_2314\"}";
    public static final String LOGIN_EMPTY_JSON = "{}";
}
