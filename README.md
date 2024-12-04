# Rule Engine

## Introduction    

The Rule Engine Application is a Spring Boot-based microservice designed to evaluate business rules dynamically. These rules, represented in a human-readable format, are transformed into an Abstract Syntax Tree (AST) for evaluation against user-provided data. The application allows the creation, evaluation, combination, and persistence of rules, which can range from simple comparisons to complex logical expressions involving multiple attributes. With its flexibility and scalability, the system can handle various business logic scenarios effectively, making it ideal for applications requiring dynamic decision-making based on user attributes like age, salary, and department.

## Technologies Used  

The project is built using the following technologies:  
- **Java 23**: The latest version of Java, providing modern language features and enhancements.  
- **Spring Boot 3.3.4**: A powerful framework for building microservices and REST APIs.  
- **Spring Data JPA**: Used for data access and persistence with the H2 in-memory database.  
- **H2 Database**: A lightweight, in-memory database used for rapid testing and development.  
- **Lombok**: A Java library that simplifies code with annotations, used for reducing boilerplate in entity and model classes.  
- **Maven**: Dependency management and build tool used for packaging the application.  

## Setup Instructions    

### 1. Clone the Repository  
Clone the project repository from GitHub using the following command:    
git clone https://github.com/Venkat546/Rule-Engine-Application.git  
cd Rule-Engine-Application  


### 2. Build the Application  
Use Maven to clean and build the project:  
mvn clean install


### 3. Run the Application  
Start the Spring Boot application using Maven:    
mvn spring-boot:run  


Once the application is running, it will be accessible at `http://localhost:8080`.

### 4. Access the H2 Database  
The application uses an in-memory H2 database, which can be accessed via the H2 console at:  
- **URL**: `http://localhost:8080/h2-console`  
- **JDBC URL**: `jdbc:h2:mem:testdb`  
- **Username**: `sa`  
- **Password**: `password`

## Dependencies    

All necessary dependencies are already included in the project's `pom.xml` file. You do not need to manually add them; Maven will automatically download and manage these dependencies when you build the project. These dependencies include:  
- **Spring Boot Starter Data JPA**: Manages data persistence using JPA.  
- **H2 Database**: An in-memory database used for testing.  
- **Lombok**: Reduces boilerplate code.  
- **Spring Boot Starter Test**: Provides tools for writing unit and integration tests.  
- **Spring Boot Starter Web**: Provides REST API functionality.  

Just ensure that Maven is installed and properly set up on your system, and then you can build and run the project as described in the instructions.

## API Endpoints  

The application provides the following REST API endpoints for creating, combining, and evaluating rules:  

| Method | Endpoint               | Description                        |
|--------|------------------------|------------------------------------|
| POST   | `/api/rules/create`     | Creates a new rule                |
| POST   | `/api/rules/evaluate`   | Evaluates a rule against user data|
| POST   | `/api/rules/combine`    | Combines multiple rules into a single AST |
| POST   | `/api/rules/save`       | Saves a rule to the database      |
| GET    | `/api/rules/get/{id}`   | Retrieves a rule by its ID        |

## Request and Response Examples

### 1. Create a Rule  

  **Request:**    
      ```json  
            {  
            "ruleString": "age > 30 AND salary > 50000"  
            }  

  **Response:    
            {    
            "type": "operator",    
            "value": "AND",    
            "left": {  
             "type": "operand",  
              "value": "age > 30"  
              },  
              "right": {  
              "type": "operand",  
              "value": "salary > 50000"  
                }  
              }  
              
### 2. Evaluate a Rule  

**Request:  
          {  
          "data": {  
          "age": 32,  
          "Salary": 60000  
          }  
          }  

**Response:  
          True  

### 3. Combine Rules    

  **Request:  
                {  
                "age > 30",    
                "salary > 50000"  
                }  

  **Response:  
                {  
                "type": "operator",  
                "value": "AND",  
                "left": {  
                "type": "operand",  
                "value": "age > 30"  
                  },  
                  "right": {  
                  "type": "operand",  
                  "value": "salary > 50000"  
                  }  
                  }  

### 4. Get a Rule by ID  

  **Request:  
             GET /api/rules/get/{id}  

  **Response:  
                {  
                  "id": 1,  
                 "ruleString": "age > 30 AND salary > 50000"  
                }  
                {  
                  "id": 1,  
                  "ruleString": "age > 30 AND salary > 50000"  
                }  

## Testing the API  

You can test the REST API endpoints using Postman by following these steps:  

-Open Postman and create a new request.  
-Set the HTTP method (e.g., POST or GET).  
-Enter the endpoint URL (e.g., http://localhost:8080/api/rules/create).  
-For POST requests, set the request body in JSON format.  
-Send the request and check the response in the Postman interface.


## Conclusion  

This Rule Engine Application is a robust system designed to evaluate complex business rules dynamically. It uses an Abstract Syntax Tree (AST) to structure and evaluate rules efficiently, making it suitable for decision-making based on multiple user attributes. The system allows for the creation, combination, and evaluation of rules, with a focus on flexibility and scalability. Future extensions could include more advanced validation and support for user-defined functions to handle even more intricate conditions.  






