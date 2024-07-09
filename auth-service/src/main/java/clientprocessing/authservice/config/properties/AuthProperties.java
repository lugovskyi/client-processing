package clientprocessing.authservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.auth")
public record AuthProperties(
        String sid) {
}
