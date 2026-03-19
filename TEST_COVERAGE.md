# Test Coverage Summary

## Total Tests: 37

### Products API - 12 Tests
1. testGetAllProducts - GET /products
2. testGetSingleProduct - GET /products/{id}
3. testGetAllCategories - GET /products/categories
4. testGetProductsByCategory - GET /products/category/{category}
5. testCreateProduct - POST /products
6. testUpdateProduct - PUT /products/{id}
7. testDeleteProduct - DELETE /products/{id}
8. testGetInvalidProduct - GET /products/{invalid_id}
9. testGetLimitedProducts - GET /products?limit=5
10. testGetSortedProducts - GET /products?sort=desc
11. testProductResponseSchema - Validate response structure
12. testCreateProductWithInvalidData - Error handling

### Carts API - 9 Tests
1. testGetAllCarts - GET /carts
2. testGetSingleCart - GET /carts/{id}
3. testCreateCart - POST /carts
4. testUpdateCart - PUT /carts/{id}
5. testDeleteCart - DELETE /carts/{id}
6. testGetUserCarts - GET /carts/user/{userId}
7. testGetCartsWithDateRange - GET /carts?startdate=X&enddate=Y
8. testCartResponseSchema - Validate response structure
9. testCreateCartWithInvalidData - Error handling

### Users API - 10 Tests
1. testGetAllUsers - GET /users
2. testGetSingleUser - GET /users/{id}
3. testCreateUser - POST /users
4. testUpdateUser - PUT /users/{id}
5. testDeleteUser - DELETE /users/{id}
6. testGetLimitedUsers - GET /users?limit=5
7. testGetSortedUsers - GET /users?sort=desc
8. testUserResponseSchema - Validate response structure
9. testCreateUserWithInvalidData - Error handling
10. testGetInvalidUser - GET /users/{invalid_id}

### Auth API - 6 Tests
1. testSuccessfulLogin - POST /auth/login (valid credentials)
2. testLoginWithInvalidCredentials - POST /auth/login (401)
3. testLoginWithMissingUsername - POST /auth/login (400)
4. testLoginWithMissingPassword - POST /auth/login (400)
5. testLoginWithEmptyBody - POST /auth/login (400)
6. testLoginResponseSchema - Validate token response

## Test Categories

### Functional Tests (26)
- CRUD operations for all endpoints
- Query parameter handling
- Path parameter handling

### Schema Validation Tests (4)
- Product response structure
- Cart response structure
- User response structure
- Auth token response

### Error Handling Tests (7)
- Invalid product data
- Invalid cart data
- Invalid user data
- Invalid credentials
- Missing required fields
- Invalid IDs
- Empty request bodies

## Coverage Highlights

✅ All CRUD operations tested
✅ Query parameters (limit, sort, date range)
✅ Response schema validation
✅ Error scenarios (400, 401)
✅ Edge cases (invalid IDs, empty data)
✅ Positive and negative test cases
