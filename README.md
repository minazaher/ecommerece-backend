# Ecommerce Backend

This repository contains the backend code for an ecommerce website. The code is written in Java using Spring Boot, Spring Security, JPA Hibernate, and MariaDB.

## Features

- Built using Java Spring Boot and Spring Data JPA and Hibernate to interact with the database, manage entities, and perform CRUD operations.
- Implements Spring Security for user authentication and authorization, with roles assigned to users based on their permissions. Additionally, password encryption and custom JWT tokens are implemented to enhance security.
- Supports the use of JWT bearer custom tokens for authentication and authorization.
- Allows users to order products, browse the catalog, and use wishlists.
- Allows admins to update orders data and products.
- The code applies the SOLID principles to ensure good software design and maintainability.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine:

```
git clone https://github.com/minazaher/ecommrece-backend.git
```

2. Install dependencies:

```
cd ecommrece-backend
mvn install
```

3. Start the server:

```
mvn spring-boot:run
```

4. The server will be running at http://localhost:8080

## API Endpoints

The following API endpoints are available:

- GET /products/all - Get all products
- GET /products?page={page_number} - Get a products page 
- POST /admin/addProduct - Create a new product
- GET /orders/user?userId={user_id} - Get an order by User ID
- GET /admin/orders?orderId={order_id} - Get an order by ID

## Security

The project uses Spring Security to handle authentication and authorization. You can configure the security settings in the `SecurityConfig` class.

## Database

The project uses MariaDB as the database. You can configure the database settings in the `application.properties` file.
