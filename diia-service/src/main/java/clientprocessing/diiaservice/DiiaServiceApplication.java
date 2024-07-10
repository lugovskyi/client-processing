package clientprocessing.diiaservice;

import clientprocessing.diiaservice.config.properties.MonitorProperties;
import clientprocessing.diiaservice.config.properties.RabbitMqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RabbitMqProperties.class, MonitorProperties.class})
@SpringBootApplication
public class DiiaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiiaServiceApplication.class, args);
    }

}
