# Student Management System

This is a Spring Boot-based Student Management System built using:

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 In-Memory Database
- Springdoc OpenAPI for Swagger UI

## Features

- Manage Students and Courses (CRUD)
- RESTful APIs for student and course operations
- Auto-generated Swagger documentation
- Entity and proper layer separation (Controller, Service, Repository)

## How to Run

1. Make sure Java 17 and Maven are installed.
2. Navigate to the project folder.
3. Run the application using: mvn spring-boot:run or run 
4. Access Swagger UI at: http://localhost:8080/swagger-ui.html
5. Can test all APIs via swagger

Folder Structure
controller/ - REST Controllers

service/ - Business Logic

repository/ - Data Access Layer

dto/ - DTOs for Student

model/ - JPA Entities