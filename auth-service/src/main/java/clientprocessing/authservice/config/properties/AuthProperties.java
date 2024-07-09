package clientprocessing.authservice.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.auth")
public record AuthProperties(
        @Value("${default.sid}") String sid
) {
}
