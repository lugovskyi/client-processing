package clientprocessing.fraudservice.service.external;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqProvider {
    private final AmqpTemplate amqpTemplate;

    public <T> void publishMessage(T message) {
        log.info("publish message: {}", message);
        amqpTemplate.convertAndSend(message);
    }
}
