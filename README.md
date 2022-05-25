# Component Test Framework Example Service - Multi Module Maven Project

Spring Boot application that showcases the component-test-framework for component testing as part of a multi module maven project.

The framework is available on Maven Central.  View usage here:

https://github.com/lydtechconsulting/component-test-framework

This example application uses Kafka as the broker, Postgres as the database, and uses the Transactional Outbox pattern with 
Debezium (Kafka Connect) for Change Data Capture (CDC) to publish outbound events.

The service calls out to a third party service via REST.  This is represented by a simulator in the component tests.

## Component Tests

The component tests treat the application as a black box performing end-to-end testing and proving the deployment and configuration in a local environment.

Build Spring Boot application jar:
```
mvn clean install
```

Build Docker container (in the serivce directory):
```
docker build -t ct/ctf-example-mm-service:latest .
```

Run tests:
```
mvn test -Pcomponent
```

Run tests leaving containers up:
```
mvn test -Pcomponent -Dcontainers.stayup
```
