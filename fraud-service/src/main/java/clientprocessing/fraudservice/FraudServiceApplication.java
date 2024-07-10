package clientprocessing.fraudservice;

import clientprocessing.fraudservice.config.properties.MonitorProperties;
import clientprocessing.fraudservice.config.properties.RabbitMqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RabbitMqProperties.class, MonitorProperties.class})
@SpringBootApplication
public class FraudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudServiceApplication.class, args);
    }

}
