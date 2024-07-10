package clientprocessing.fraudservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqListenerContainerService {

    private final RabbitListenerEndpointRegistry endpointRegistry;

    public boolean startListening(String containerId) {
        MessageListenerContainer container = endpointRegistry.getListenerContainer(containerId);
        if (!container.isRunning()) {
            log.info("starting container {}", containerId);
            container.start();
        } else {
            log.info("Container {} has been already started", containerId);
        }
        return true;
    }

    public boolean stopListening(String containerId) {
        MessageListenerContainer container = endpointRegistry.getListenerContainer(containerId);
        if (container.isRunning()) {
            log.info("Stopping container {}", containerId);
            container.stop();
        } else {
            log.info("Container {} has been already stopped", containerId);
        }
        return true;
    }
}
