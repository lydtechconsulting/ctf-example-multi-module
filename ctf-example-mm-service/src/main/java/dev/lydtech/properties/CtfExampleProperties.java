package dev.lydtech.properties;

import java.net.URL;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties("ctfexamplemm")
@Getter
@Setter
@Validated
public class CtfExampleProperties {
    @NotNull
    private URL thirdPartyEndpoint;

    @NotNull
    private URL externalServiceEndpoint;
}
