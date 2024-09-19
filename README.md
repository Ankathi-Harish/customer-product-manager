# Customer and Product Management

## Overview
This Spring Boot application provides RESTful APIs for managing customers and products. It includes support for Swagger documentation, logging, Redis caching, and Kafka events.

## Technologies
- JDK 17
- Spring Boot 3.3.3
- Oracle Database
- Redis
- Kafka

## API Endpoints

### Customers
- **POST** `/api/customers` - Create a new customer
- **GET** `/api/customers` - Get all customers
- **GET** `/api/customers/{id}` - Get a customer by ID
- **PUT** `/api/customers/{id}` - Update a customer by ID
- **PATCH** `/api/customers/{id}` - Partially update a customer by ID
- **DELETE** `/api/customers/{id}` - Delete a customer by ID

### Products
- **POST** `/api/products` - Create a new product
- **GET** `/api/products` - Get all products
- **GET** `/api/products/{id}` - Get a product by ID
- **PUT** `/api/products/{id}` - Update a product by ID
- **PATCH** `/api/products/{id}` - Partially update a product by ID
- **DELETE** `/api/products/{id}` - Delete a product by ID

## Configuration
Update `application.properties` with your Oracle DB, Redis, and Kafka configurations.

## Running the Application
1. Clone the repository.
2. Run `mvn clean install` to build the project.
3. Run `mvn spring-boot:run` to start the application.

## Swagger
Access Swagger UI at `/swagger-ui.html` for API documentation and testing.

## Logging
Logs can be found in the console or log files.

## Caching
Redis is used for caching.

## Events
Kafka is used for event management.
