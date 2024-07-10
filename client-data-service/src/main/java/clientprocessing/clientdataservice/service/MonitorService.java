package clientprocessing.clientdataservice.service;

import clientprocessing.clientdataservice.config.properties.MonitorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class MonitorService {
    private final RabbitMqListenerContainerService rabbitService;
    private final MonitorProperties monitorProperties;

    public void stopServicesForMonitoring() {
        if (Boolean.TRUE.equals(monitorProperties.isEnabled())) {
            boolean isStoppedListening = rabbitService.stopListening(RabbitConsumer.CONTAINER_ID);
            if (isStoppedListening) {
                log.info("Monitoring is enable");
            } else {
                log.info("Monitoring can't enable");
            }
        }
    }

    @Scheduled(fixedDelayString = "${monitor.interval}")
    public void startServicesAfterMonitoring() {
        if (Boolean.TRUE.equals(monitorProperties.isEnabled())) {
            boolean isStoppedListening = rabbitService.startListening(RabbitConsumer.CONTAINER_ID);
            if (isStoppedListening) {
                log.info("Monitoring is disable");
            } else {
                log.info("Monitoring can't disable");
            }
        }
    }
}
