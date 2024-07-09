package clientprocessing.authservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitProperties(
        String host,
        int port,
        String username,
        String password,
        String exchange,
        String queue,
        String routingKey
) {
}
