# Employee Management Application

This repository contains two example projects demonstrating different approaches for managing employee data in Java:

1. **`crud-jpa-hibernate`** – JPA + Hibernate CRUD demo  
2. **`rest-api`** – Spring Boot REST API (Spring Data JPA, Spring Data REST, OpenAPI/Swagger)

---

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Project Setup](#project-setup)
  - [Prerequisites](#prerequisites)
  - [Running `crud-jpa-hibernate`](#running-crud-jpa-hibernate)
  - [Running `rest-api`](#running-rest-api)
- [Accessing the REST API](#accessing-the-rest-api)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- Manage employee records (Create, Read, Update, Delete)  
- JPA + Hibernate example for database interactions  
- REST API using Spring Boot and Spring Data JPA  
- OpenAPI/Swagger documentation for REST endpoints  

---

## Technologies

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Maven  
- H2 / MySQL (configurable in application.properties)  
- Swagger / OpenAPI for REST API documentation  

---

## Project Setup

### Prerequisites

Before running the projects, make sure you have:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) installed  
- [Maven](https://maven.apache.org/install.html) (or use the included Maven wrapper `./mvnw`)  
- [Git](https://git-scm.com/) installed  

Clone this repository:

```bash
git clone https://github.com/your-username/employee-management-app.git
cd employee-management-app
