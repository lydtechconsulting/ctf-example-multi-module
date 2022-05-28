package com.thirdparty.properties;

import java.net.URL;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties("thirdparty")
@Getter
@Setter
@Validated
public class ThirdPartyProperties {
    @NotNull
    private String context;
}
