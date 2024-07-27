# Cafe Management System

The Cafe Management System (CMS) is a comprehensive application designed to streamline cafe operations, improve efficiency, and enhance customer satisfaction. Built using Java, Spring Boot, and Angular, this system offers robust and secure functionalities.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Application Properties](#application-properties)
- [Spring Boot Code](#spring-boot-code)
- [Maven Dependencies](#maven-dependencies)
- [Angular Code](#angular-code)
- [License](#license)

## Features

- Efficient cafe operations management
- User-friendly interface
- Secure data handling
- Scalable architecture

## Technologies Used

- Java
- Spring Boot
- Angular
- MySQL

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Node.js and npm
- Maven
- MySQL

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/cafe-management-system.git
    cd cafe-management-system
    ```

2. Set up the MySQL database:
    ```sql
    CREATE DATABASE cafe_management_system;
    ```

3. Update the application properties file with your database credentials.

4. Build the Spring Boot backend:
    ```bash
    cd backend
    mvn clean install
    ```

5. Install Angular dependencies and build the frontend:
    ```bash
    cd frontend
    npm install
    npm run build
    ```

### Running the Application

1. Start the Spring Boot backend:
    ```bash
    cd backend
    mvn spring-boot:run
    ```

2. Start the Angular frontend:
    ```bash
    cd frontend
    npm start
    ```

## Application Properties

Update the `application.properties` file with your database connection details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafe_management_system
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
