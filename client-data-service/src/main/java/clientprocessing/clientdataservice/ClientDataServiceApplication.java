package clientprocessing.clientdataservice;

import clientprocessing.clientdataservice.config.properties.MonitorProperties;
import clientprocessing.clientdataservice.config.properties.RabbitMqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RabbitMqProperties.class, MonitorProperties.class})
@SpringBootApplication
public class ClientDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDataServiceApplication.class, args);
    }

}
