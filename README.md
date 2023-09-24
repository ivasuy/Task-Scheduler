# Task Management System

Creating a RESTful API for a task management system with features like task prioritization, due dates, caching, and task assignment.

---

## Data Models:

### **User Table:** represents user details in the system.

### **Task Table:** is responsible for storing task-related information within the system.

---

## API End Points :

### **Admin Controller**

- Base URL: **`/api/admin`**
    1. **Create User**
        - Endpoint: **`POST /createUser`**
        - Description: Create a new user with the provided user details.
        
        ```json
        Request Body: UserRequest
        {
            "userName" : "string",
            "userEmail" : "string",
            "userPassword" : "string"
        }
        ```
        
        - Response: String (Success message)
    2. **Delete User**
        - Endpoint: **`DELETE /deleteUser/{userId}`**
        - Description: Delete a user with the specified user ID.
        - Path Parameter: **`userId`** (Long)
        - Response: String (Success message)

### **Authentication Controller**

- Base URL: **`/api/auth`**
    1. **Register**
        - Endpoint: **`POST /register`**
        - Description: Register a new user with the provided registration details.
        
        ```json
        Request Body: RegisterRequest
        {
            "name" : "string",
            "email" : "string",
            "password" : "string",
            "role" : "ADMIN"/"USER"
        }
        ```
        
        ```json
        Response: AuthenticationResponse
        {
            "token" : "string"
        }
        ```
        
    2. **Authenticate**
        - Endpoint: **`POST /authenticate`**
        - Description: Authenticate a user with the provided authentication request.
        
        ```json
        Request Body: AuthenticationRequest
        {
            "email" : "string",
            "password" : "string"
        }
        ```
        
        ```json
        Response: AuthenticationResponse
        {
            "token" : "string"
        }
        ```
        

### **User Controller**

- Base URL: **`/api/user`**
    1. **Update User Details**
        - Endpoint: **`PUT /{userId}`**
        - Description: Update the details of a user with the provided user ID.
        - Path Parameter: **`userId`** (Long)
        
        ```json
        Request Body: UserRequest
        {
            "userName" : "string",
            "userEmail" : "string",
            "userPassword" : "string"
        }
        ```
        
        - Response: String (Success message)

### **Task Controller**

- Base URL: **`/api/task`**
    1. **Create Task**
        - Endpoint: **`POST /`**
        - Description: Create a new task associated with a user.
        
        ```json
        Request Body: TaskRequest
        {
            "taskDescription" : "string",
            "taskStatus" : "DONE"/"PENDING",
            "taskPriority" : 0,
            "taskCategory" : "WORK"/"PERSONAL"/"SHOPPING"/"HEALTH"/"EDUCATION"/"OTHER"
        }
        ```
        
        - Request Parameter: **`userId`** (Long)
        - Response: String (Success message)
    2. **Get Tasks by User ID**
        - Endpoint: **`GET /{userId}`**
        - Description: Retrieve tasks associated with a specific user.
        - Path Parameter: **`userId`** (Long)
        
        ```json
        Response: List of TaskResponse
        {
          "taskDescription": "string",
          "taskStatus": "DONE"/"PENDING",
          "taskPriority": 0,
          "taskCategory": "WORK"/"PERSONAL"/"SHOPPING"/"HEALTH"/"EDUCATION"/"OTHER",
          "taskCreationTime": "2023-09-15T10:00:00Z",
          "taskUpdationTime": "2023-09-15T12:30:00Z"
        }
        ```
        
    3. **Update Task**
        - Endpoint: **`PUT /{taskId}`**
        - Description: Update an existing task with the provided details.
        - Path Parameter: **`taskId`** (Long)
        
        ```json
        Request Body: TaskRequest
        {
            "taskDescription" : "string",
            "taskStatus" : "DONE"/"PENDING",
            "taskPriority" : 0,
            "taskCategory" : "WORK"/"PERSONAL"/"SHOPPING"/"HEALTH"/"EDUCATION"/"OTHER"
        }
        ```
        
        - Request Parameter: **`userId`** (Long)
        - Response: String (Success message)
    4. **Delete Task**
        - Endpoint: **`DELETE /{taskId}`**
        - Description: Delete a task with the specified task ID.
        - Path Parameter: **`taskId`** (Long)
        - Request Parameter: **`userId`** (Long)
        - Response: String (Success message)

### **Security Configuration**

The security configuration defines access control for the endpoints based on roles.

- **`/api/auth/**`** endpoints are open to all (permit All).
- **`/api/admin/**`** endpoints require the role "ADMIN."
- **`/api/task/**`** endpoints require the role "USER/ADMIN."

**Endpoint: /v3/api-docs/**

You can use this endpoint to explore the API documentation, including details about available endpoints, request and response formats, and more.

**Endpoint: /h2-console/**

You can use this endpoint to manage and inspect the H2 database used by  Spring Boot application.

---

## **Tech Stack :**

Java, Spring / Spring Boot, JPA, Hibernate, H2, Spring Security,  JWT, Maven, JUnit, Spring doc, Spring Cache

---
