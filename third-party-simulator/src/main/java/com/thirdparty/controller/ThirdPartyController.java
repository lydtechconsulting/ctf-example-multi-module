package com.thirdparty.controller;

import com.thirdparty.properties.ThirdPartyProperties;
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
public class ThirdPartyController {

    @Autowired
    private ThirdPartyProperties properties;

    @GetMapping("/thirdparty/api/{id}")
    public ResponseEntity<String> api(@PathVariable String id) {;
        log.info("Third party simulator called with id {}", id);
        return ResponseEntity.ok("Third party simulator ("+properties.getContext()+") called successfully with id: "+id+".");
    }
}
