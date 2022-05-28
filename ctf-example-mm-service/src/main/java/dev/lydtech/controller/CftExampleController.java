package dev.lydtech.controller;

import dev.lydtech.api.CftExampleResponse;
import dev.lydtech.service.CtfExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class CftExampleController {

    @Autowired
    private CtfExampleService service;

    @GetMapping("/api/{id}")
    public ResponseEntity<CftExampleResponse> api(@PathVariable String id) {
        log.info("Received request with id: {}", id);
        String result = service.process(id);
        log.info("Response is: {}", result);
        return ResponseEntity.ok(new CftExampleResponse(result));
    }
}
