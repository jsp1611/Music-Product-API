# Music Product API

A simple Spring Boot based API for working with music product information stored within an embedded H2 database using
JPA for dynamic query handling.

## Running locally

The main entry point for the application is within the class `MusicProductApplication`. This can be
run from within an IDE, such as IntelliJ or Eclipse, or the Maven Spring Boot plugin can be used. 

Data is stored on the file system, relative to the run location, under the `data` directory. Once started, the
application can be explored using the [Swagger document](http://localhost:8080/swagger-ui/index.html), or by directly [browsing the database](http://localhost:8080/h2-console) using H2 console. 

Sample data is loaded in from `.sql` scripts at start up. At the moment, this is an unconditional load, so the same data will be added again if the database is not cleared down between restarts. 


