package dev.lydtech.service;

import dev.lydtech.exception.CtfExampleException;
import dev.lydtech.properties.CtfExampleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CtfExampleService {

    @Autowired
    private CtfExampleProperties properties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Calls the external services and returns their responses.
     */
    public String process(String id) {
        String thirdPartyResult = callThirdParty(id).getBody();
        String externalServiceResult = callExternalService(id).getBody();
        return thirdPartyResult + "  " + externalServiceResult;
    }

    /**
     * Make a REST call to the third party service.
     */
    private ResponseEntity<String> callThirdParty(String id) {
        try {
            log.info("Calling third party with id {}", id);
            return restTemplate.getForEntity(properties.getThirdPartyEndpoint() + id, String.class);
        } catch (Exception e) {
            log.error("Error calling third party API", e);
            throw new CtfExampleException(e);
        }
    }

    /**
     * Make a REST call to the external service.
     */
    private ResponseEntity<String> callExternalService(String id) {
        try {
            log.info("Calling external service with id {}", id);
            return restTemplate.getForEntity(properties.getExternalServiceEndpoint() + id, String.class);
        } catch (Exception e) {
            log.error("Error calling external service API", e);
            throw new CtfExampleException(e);
        }
    }
}
