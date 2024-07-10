package clientprocessing.fraudservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mointor")
public record MonitorProperties(
        Boolean isEnabled,
        Integer errorCount
) {
}
