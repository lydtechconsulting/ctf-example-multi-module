package dev.lydtech.component;

import java.util.UUID;

import dev.lydtech.component.framework.client.service.AdditionalContainerClient;
import dev.lydtech.component.framework.client.service.ServiceClient;
import dev.lydtech.component.framework.extension.ComponentTestExtension;
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
@ExtendWith(ComponentTestExtension.class)
public class ExampleApiCT {

    String serviceBaseUrl;
    String thirdPartyServiceBaseUrl;
    String externalServiceBaseUrl;

    @BeforeEach
    public void setup() {
        serviceBaseUrl = ServiceClient.getInstance().getBaseUrl();

        thirdPartyServiceBaseUrl = AdditionalContainerClient.getInstance().getBaseUrl("third-party-simulator");
        externalServiceBaseUrl = AdditionalContainerClient.getInstance().getBaseUrl("external-service-simulator");
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
    public void testServiceCallsToSimulators() {
        String id = UUID.randomUUID().toString();
        log.info("Test id is: {}", id);
        get(serviceBaseUrl+"/v1/api/"+id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("value", equalTo("Third party simulator (component) called successfully with id: "+id+".  External service simulator (component) called successfully with id: "+id+"."));
    }

    /**
     * Test a direct call to the third party simulator via REST to demonstrate this is possible.
     */
    @Test
    public void testDirectCallToThirdPartyServiceSimulator() {
        String id = UUID.randomUUID().toString();
        log.info("Test id is: {}", id);
        get(thirdPartyServiceBaseUrl+"/v1/thirdparty/api/"+id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body(equalTo("Third party simulator (component) called successfully with id: "+id+"."));
    }

    /**
     * Test a direct call to the external service simulator via REST to demonstrate this is possible.
     */
    @Test
    public void testDirectCallToExternalServiceSimulator() {
        String id = UUID.randomUUID().toString();
        log.info("Test id is: {}", id);
        get(externalServiceBaseUrl+"/v1/external/api/"+id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body(equalTo("External service simulator (component) called successfully with id: "+id+"."));
    }
}
