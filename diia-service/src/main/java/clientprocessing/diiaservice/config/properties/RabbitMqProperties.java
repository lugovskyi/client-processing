package clientprocessing.diiaservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitMqProperties(
        String host,
        int port,
        int consumers,
        int maxConsumers,
        String username,
        String password,
        String exchange,
        String queue,
        String routingKey
) {
}
