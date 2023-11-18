# Component Test Framework Example Service - Multi Module Maven Project

Spring Boot application that showcases the component-test-framework for component testing as part of a multi module maven project.

The framework is available on Maven Central.  View usage here:

https://github.com/lydtechconsulting/component-test-framework

This example application exposes a REST endpoint that results in a call to two different third party services via REST.  These services are represented by simulators in the component tests.

## Component Tests

The component tests treat the application as a black box performing end-to-end testing and proving the deployment and configuration in a local environment.

Build Spring Boot application jar, with Java 17:
```
mvn clean install
```

Build main service Docker container (in the `ctf-example-mm-service` directory):
```
cd ctf-example-mm-service

docker build -t ct/ctf-example-mm-service:latest .
```

Build third party simulator Docker container (in the `third-party-simulator` directory):
```
cd third-party-simulator

docker build -t ct/third-party-simulator:latest .
```
Note: ensure the docker container name (`third-party-simulator`) matches the properties file location in `component-test`: `src/test/resources/third-party-simulator/application-component.test.yml`.

Build external service simulator Docker container (in the `external-service-simulator` directory):
```
cd external-service-simulator

docker build -t ct/external-service-simulator:latest .
```
Note: ensure the docker container name (`external-service-simulator`) matches the properties file location in `component-test`: `src/test/resources/external-service-simulator/application-component.test.yml`.

## Run Tests 

### Maven

Run tests (from parent directory or `component-test` directory):
```
mvn test -Pcomponent
```

Run tests leaving containers up:
```
mvn test -Pcomponent -Dcontainers.stayup
```

### Gradle

Run tests (from `component-test` directory):

In the `component-test` module run:
```
./gradlew clean build
```

Run tests leaving containers up:
```
./gradlew clean build -Dcontainers.stayup=true
```

## Clean Up Commands

- Manual clean up (if left containers up):
```
docker rm -f $(docker ps -aq)
```
