## Database notes

This directory will be empty, aside from this file, upon check out. 

Any H2 database files written to disk will appear within this directory in the form of a file called `demo.mv.db`. The schema is manually created by Spring Boot using DDL in the file [schema.sql](../src/main/resources/schema.sql). Schema creation will be skipped if the schema already exists upon start up. To "clear down" the database between restarts, you can remove the `demo.mv.db` file, and the DDL will rerun upon restart. 

Schema locations are defined for Spring Boot in [application.yaml](../src/main/resources/application.yaml) using the property `spring.sql.init.schema-locations`. 

Sample music product data is also inserted upon start up using the DML in the file [data.sql](../src/main/resources/data.sql), with the associated [application.yaml](../src/main/resources/application.yaml) property being `spring.sql.init.data-locations`. 