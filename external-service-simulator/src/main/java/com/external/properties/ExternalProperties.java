package com.external.properties;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties("external")
@Getter
@Setter
@Validated
public class ExternalProperties {
    @NotNull
    private String context;
}
