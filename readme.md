# Spring Security Project Postman Collection

This Postman collection includes various endpoints for interacting with the Spring Security project. Below is a guide to the available endpoints, along with instructions on how to use them.

## Endpoints

### 1. Get Welcome Message
- **Endpoint:** `GET /get/welcome`
- **URL:** `http://localhost:8080/get/welcome`
- **Description:** Retrieves a welcome message.
- **Request Body:** None
- **Response Body:**
    ```json
    {
        "message": "Welcome to our application!"
    }
    ```

### 2. Register a New User
- **Endpoint:** `POST /login/newUser`
- **URL:** `http://localhost:8080/login/newUser`
- **Description:** Registers a new user with the provided details.
- **Request Body:**
    ```json
    {
        "username": "Manish",
        "password": "abc1",
        "email": "manish@gmail.com",
        "roles": "ROLE_USER"
    }
    ```
- **Response Body:**
    ```json
    {
        "message": "User registered successfully!"
    }
    ```

### 3. Authenticate User
- **Endpoint:** `POST /authenticate`
- **URL:** `http://localhost:8080/authenticate`
- **Description:** Authenticates a user and generates a JWT token.
- **Request Body:**
    ```json
    {
        "username": "Subhojit",
        "password": "abc3"
    }
    ```
- **Response Body:**
    ```json
    {
        "token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

### 4. Get Secured User Information
- **Endpoint:** `GET /get/secured/user`
- **URL:** `http://localhost:8080/get/secured/user`
- **Description:** Retrieves secured information for a user.
- **Request Body:** None
- **Response Body:**
    ```json
    {
        "username": "Manish",
        "roles": ["ROLE_USER"]
    }
    ```
- **Authorization:** Bearer token required.

### 5. Get Secured Admin Information
- **Endpoint:** `GET /get/secured/admin`
- **URL:** `http://localhost:8080/get/secured/admin`
- **Description:** Retrieves secured information for an admin.
- **Request Body:** None
- **Response Body:**
    ```json
    {
        "username": "Admin",
        "roles": ["ROLE_ADMIN"]
    }
    ```
- **Authorization:** Bearer token required.

## Usage Instructions

1. **Import the Postman Collection:**
    - Open Postman.
    - Click on `Import` in the top-left corner.
    - Select `File` and choose the Postman collection file (`.json` format).

2. **Set Up Authentication:**
    - After registering a new user, authenticate the user using the `/authenticate` endpoint to obtain the Bearer token.
    - Use this token to access the secured endpoints `/get/secured/user` and `/get/secured/admin`.

3. **Making Requests:**
    - For unsecured endpoints (`/get/welcome`, `/login/newUser`, `/authenticate`), make requests directly.
    - For secured endpoints (`/get/secured/user`, `/get/secured/admin`), include the Bearer token in the `Authorization` header.
      ```
      Authorization: Bearer <your-token-here>
      ```

4. **Example Postman Request for Secured Endpoints:**
    - Select `GET` method.
    - Enter the URL `http://localhost:8080/get/secured/user` or `http://localhost:8080/get/secured/admin`.
    - Go to the `Authorization` tab.
    - Select `Bearer Token` and paste your token in the `Token` field.
    - Click `Send`.

## Note

Ensure your Spring Security project is running locally on port 8080 to use these endpoints.

Happy Testing!
