

# Project Management System

The Project Management System is a RESTful API developed using Java 17 and Spring Boot, designed to manage project information efficiently. This system allows users to perform Create, Read, Update, and Delete (CRUD) operations on projects, storing the data in an in-memory H2 database. Each project can be defined with attributes such as name, description, start date, and end date.




## Features

- Create Project: Add new projects with relevant details.
- Read Projects: Retrieve all projects or get specific project details by ID.
- Update Project: Modify existing project information.
- Delete Project: Remove projects from the system.


## Technologies Used

- Java 17: The latest Long-Term Support (LTS) version of Java, providing new features and performance improvements.
- Spring Boot: A framework that simplifies the development of production-ready applications.
- Spring Data JPA: Facilitates database operations using the Java Persistence API.
- H2 Database: An in-memory database used for quick and easy data storage during development.
- Swagger: Integrated for API documentation, making it easy to test and understand the endpoints.
## Getting Started
This guide will help you set up and run the Project Management System on your local machine.

Prerequisites

- Java 17 or later
- Maven

## Setup Instructions

1. Clone the repository:

```bash
  git clone https://github.com/yourusername/project-management.git
cd project-management
```
2. Build and run the application:

```bash
  ./mvnw spring-boot:run
```

3. Access the application:

- H2 Database Console: http://localhost:8080/h2-console


    - JDBC URL : jdbc:h2:mem:testdb
    - Username : sa
    - Password : 

    here password can be anything which you desired to be used in this project I used no password
  
    
## API Endpoints

- Create a Project: POST /projects
- Get All Projects: GET /projects
- Get a Project by ID: GET /projects/{id}
- Update a Project: PUT /projects/{id}
- Delete a Project: DELETE /projects/{id}

## Testing

Run the tests using the following command:

```bash
  ./mvnw test
```

Feel free to adjust any part of the description to better fit your project specifics or personal preferences.
