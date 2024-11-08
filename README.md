# Spring Boot Transformer App

This is a Transformer App

### Build
$ mvn clean build

### Build without tests
$ mvn clean build -DskipTests

## Start locally
$ mvn spring-boot:run

## Start locally with no tests
$ mvn spring-boot:run -DskipTests

### View Swagger
Open http://localhost:8080/api/v1/swagger-ui/index.html#/data-transformer-controller/

## Environment setup
Docker compose sets up all services needed for spring boot to run.
```
docker-compose up
``` 
