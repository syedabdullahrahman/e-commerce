# Simple E-commerce REST API

## Table of Contents
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Database Schema](#database-schema)

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL 
- Maven
- Liquibase
- OpenAPI
- Docker
- Spring Data REST  etc.

## Features

- API to return the wish list of a customer.
- API to return the total sale amount of the current day.
- API to return the max sale day of a certain time range.
- API to return top N selling items of all time (based on total sale amount).
- API to return top N selling items of the last month (based on number of sales).

## Getting Started

### Prerequisites

List any prerequisites or requirements to follow your instructions (e.g., Java 17+, MySQL server).

### Installation

Step-by-step instructions on how to install and configure your project.

1. Clone the repository.
   ```bash
   git clone https://github.com/syedabdullahrahman/e-commerce.git
   ```
2. Navigate to the project directory.

3. Build the project using Maven.
```bash
mvn clean install
```
4. Run the application
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Database Schema
The database schema is from [MySQL Sample Database](https://www.mysqltutorial.org/getting-started-with-mysql/mysql-sample-database/)

Here is the schema diagram:

![schema](mysql-sample-database.png)