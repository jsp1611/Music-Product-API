# Demo Music Product API

A simple demonstration Spring Boot microservice, written in Java, that can act as a template for similar services. This project has a REST based API for working with music product information, which is stored within an embedded H2 database. JPA for dynamic query handling.

## Key technologies & patterns

The project demonstrates the usage of the following technologies:

* Spring Boot - core framework for the application
* Jackson - for JSON serialization and deserialization at the API level
* Lombok - reduces boilerplate Java code
* H2 - for use as an embedded database
* JPA - used for query generation 
* Swagger - annotations on the Controllers are used to generate a Swagger UI

Other implementation choices within the microservice include:

* REST API - the `/api/product` endpoint is *mostly* RESTful, stopping short of HATEOAS, at Level 2 on the Richardson Maturity Model
* DTO - hold data for transport over the wire and are constructed and parsed at the Controller level

## Building locally

The project can be built locally using Maven `3.9.12` with `JDK 25.0.1` by invoking Maven at the base of the project with:

    mvn clean package

Only one Maven profile is in use, which will compile the source, run tests, and build the target `jar` file.

## Running locally

The main entry point for the application is within the class `MusicProductApplication`. This can be run from within an IDE, such as IntelliJ or Eclipse, or directly on the command line using the Maven Spring Boot plugin with the following:

    mvn spring-boot:run

Data is stored on the file system, relative to the run location, under the `data` directory. Once started, the
application can be explored using the generated Swagger UI:

    http://localhost:8080/swagger-ui/index.html

The H2 console is also available for direct browsing:

    http://localhost:8080/h2-console

Credentials are available within the `application.yaml` file.

Upon application start, the database schema is created if it is not already present using `schema.sql`, and sample data is loaded from `data.sql`. The sample data is removed and re-added on each start up based on the name of the `Store` value in each row. Any data the user adds will be retained and written to disk under the project `data` directory. To clear down all data, remove the content of the [`data` directory](data/DATABASE.md). 

## Links

Links related to the project:

- [Local Swagger UI](http://localhost:8080/swagger-ui/index.html)
- [Local H2 Console](http://localhost:8080/h2-console)
- [Project Lombok](https://projectlombok.org/)
- [Spring Boot](https://docs.spring.io/spring-boot/documentation.html)
- [H2 Database Engine](https://www.h2database.com/html/main.html)
- [Swagger 2.0 Annotations](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations)