# customer-api Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

## Create data base

```
CREATE DATABASE manage
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


\c manage;

CREATE TABLE  customer
(
    customer_id  serial PRIMARY KEY,
    first_name  varchar(60) NOT NULL,
    middle_name varchar(60),
    last_name   varchar(60) NOT NULL,
    suffix      varchar(6),
    email       varchar(60) NOT NULL UNIQUE,
    phone       varchar(15) NOT NULL
);

insert into  customer
(
    customer_id ,
    first_name,
    middle_name,
    last_name   ,
    suffix     ,
    email  ,
    phone 
) values 
(1, 'Ryacho',    'Louis',     'MEN', 'Mr',  'ryacho@gmail.com', '0112233445'),
(2, 'Akka',      'Charlotte', 'MEN', 'Mme', 'akka@gmail.com',   '0112233446'),
(3, 'Mrzk',      'Gaspard',   'MEN', 'Mr',  'mrzk@gmail.com',   '0112233447');

```

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

Other method :
```shell script
mvn clean install

java -jar target\quarkus-mysql-0.0.1-runner.jar
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8585/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/customer-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- YAML Configuration ([guide](https://quarkus.io/guides/config#yaml)): Use YAML to configure your Quarkus application

## Provided Code

### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
