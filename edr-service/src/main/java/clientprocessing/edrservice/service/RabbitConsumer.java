package clientprocessing.edrservice.service;

import clientprocessing.edrservice.config.properties.MonitorProperties;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumer {

    public static final String CONTAINER_ID = "auth-queue-container";
    public static final AtomicInteger exceptionCount = new AtomicInteger(0);

    private final MonitorService monitorService;
    private final ProcessingService processingService;
    private final MonitorProperties monitorProperties;

    @RabbitListener(queues = "${rabbitmq.listened.queue}", id = CONTAINER_ID)
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            log.info("received message: {}", message);
            processingService.processMessage(message);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("Error during message processing: {}", e.getMessage());
            checkExceptionCounter();
            log.error("sendenig to dead queue");
            channel.basicNack(tag, false, false);
        }
    }

    private void checkExceptionCounter() {
        log.error("Error count: {}", exceptionCount.incrementAndGet());
        if (exceptionCount.intValue() >= monitorProperties.errorCount()) {
            exceptionCount.set(0);
            if (Boolean.TRUE.equals(monitorProperties.isEnabled())) {
                monitorService.stopServicesForMonitoring();
            }
        }
    }
}
