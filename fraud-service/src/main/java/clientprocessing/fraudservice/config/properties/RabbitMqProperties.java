package clientprocessing.fraudservice.config.properties;

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
        String deadExchange,
        String queue,
        String deadQueue,
        String routingKey
) {
}
