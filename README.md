# Task-Manager

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Setup Database
For running locally edit `application.properties` with your mysql username and password. create database with named `task_manager` 
```
spring.datasource.url = jdbc:mysql://localhost:3306/task_manager?useSSL=true
spring.datasource.username = your username
spring.datasource.password = your password
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.sadman.taskmanager.TaskManagerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## REST API Endpoints

All inputs and outputs use JSON format.

**Use Postman for API Call**

Credential: 

username - admin, password - 12345678, role - ADMIN

username - user, password - 12345678, role - USER

## Login
**You send:**  Your  login credentials.
**You get:** An `API-Token` with wich you can make further actions.

**Request:**
```json
localhost:8080/api/auth/signin
POST 
Accept: application/json
Content-Type: application/json

{
    "username": "admin",
    "password": "12345678" 
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": 1,
    "username": "admin",
    "email": "admin@gmail.com",
    "role": "ADMIN",
    "tokenType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyOTk1NTYwNCwiZXhwIjoxNjMwMDQyMDA0fQ.l_QXkv7QXCWPidk_7MJZO65WlkfylhMdAZ3awMtuVjuzt-sAitQF3h_6GPPUX5QqMW3z8LbPtEHYkswwrP5i0w"
}
```
**Failed Response:**
```json
HTTP/1.1 401 Unauthorized
Content-Type: application/json

{
    "timestamp": "2021-08-26T05:30:29.828+00:00",
    "status": 401,
    "error": "Unauthorized",
    "path": "/api/auth/signin"
}
``` 

## Create Project
**You send:**  Project Name with authorization token.
**You get:** A response message of creating project.

**Request:**
```json
localhost:8080/api/projects
POST 
Accept: application/json
Content-Type: application/json

{
  "projectName": "Your Project Name"
}
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

{
    "Your Project Name is created by Mr Admin"
}
```
**Failed Response:**
```json
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
    "message": "Error: Duplicate Project Name!"
}
``` 

## Get All Personal Project
**You send:**  Project Name with authorization token.
**You get:** A response message of creating project.

**Request:**
```json
localhost:8080/api/projects/mine
GET 
Accept: application/json
Content-Type: application/json
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
        "projectName": "Project 3",
        "userName": "user"
    },
    {
        "projectName": "Project 4",
        "userName": "user"
    },
    {
        "projectName": "Project 5",
        "userName": "user"
    },
    {
        "projectName": "Project 8",
        "userName": "user"
    }
]
```

## Delete Project
**You send:**  Project Id with authorization token.
**You get:** A response message of deleted project.

**Request:**
```json
localhost:8080/api/projects/delete/5
DELETE 
Accept: application/json
Content-Type: application/json
```
**Successful Response:**
```json
HTTP/1.1 200 OK
Content-Type: application/json

Project is Deleted Successfully
```

