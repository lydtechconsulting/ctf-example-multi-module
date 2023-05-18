package dev.lydtech.component;

import java.util.UUID;

import dev.lydtech.component.framework.client.service.ServiceClient;
import dev.lydtech.component.framework.extension.TestContainersSetupExtension;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

/**
 * Demonstrates the service under test calling a third party service.
 */
@Slf4j
@ExtendWith(TestContainersSetupExtension.class)
public class ExampleApiCT {

    @BeforeEach
    public void setup() {
        String serviceBaseUrl = ServiceClient.getInstance().getBaseUrl();
        log.info("Service base URL is: {}", serviceBaseUrl);
        RestAssured.baseURI = serviceBaseUrl;
    }

    /**
     * An event is sent to the inbound topic for the service.
     *
     * The test makes a REST call to the service.  The service calls two external services via REST, and returns their results.
     *
     * The test asserts the REST response it is as expected.
     *
     * This test therefore verifies calling the service container via REST works, and that the Simulator containers can
     * themselves be called successfully by the service under test.
     */
    @Test
    public void testCallToThirdParty() {
        String id = UUID.randomUUID().toString();
        log.info("Test id is: {}", id);
        get("v1/api/"+id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("value", equalTo("Third party simulator (component) called successfully with id: "+id+".  External service simulator (component) called successfully with id: "+id+"."));
    }
}
