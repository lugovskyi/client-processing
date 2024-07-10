package clientprocessing.edrservice;

import clientprocessing.edrservice.config.properties.MonitorProperties;
import clientprocessing.edrservice.config.properties.RabbitMqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RabbitMqProperties.class, MonitorProperties.class})
@SpringBootApplication
public class EdrServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdrServiceApplication.class, args);
    }

}
