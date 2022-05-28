package com.external.controller;

import com.external.properties.ExternalProperties;
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
public class ExternalController {

    @Autowired
    private ExternalProperties properties;

    @GetMapping("/external/api/{id}")
    public ResponseEntity<String> api(@PathVariable String id) {;
        log.info("External service simulator called with id {}", id);
        return ResponseEntity.ok("External service simulator ("+properties.getContext()+") called successfully with id: "+id+".");
    }
}
