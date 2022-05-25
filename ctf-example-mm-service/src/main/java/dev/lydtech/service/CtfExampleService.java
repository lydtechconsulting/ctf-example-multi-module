package dev.lydtech.service;

import dev.lydtech.event.CtfExampleInboundEvent;
import dev.lydtech.exception.CtfExampleException;
import dev.lydtech.lib.KafkaClient;
import dev.lydtech.properties.CtfExampleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private KafkaClient kafkaClient;

    /**
     * Sends an outbound event in response to the received event.
     */
    public void process(String key, CtfExampleInboundEvent event) {
        callSimulator(key);
        kafkaClient.sendMessage(key, event.getData());
    }

    /**
     * Make a REST call to a simulator.
     */
    private void callSimulator(String key) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(properties.getThirdpartyEndpoint() + key, String.class);
            if (!response.getStatusCode().equals(HttpStatus.OK)) {
                throw new RuntimeException("Error: " + response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error calling thirdparty API", e);
            throw new CtfExampleException(e);
        }
    }
}
