package clientprocessing.authservice;

import clientprocessing.authservice.config.properties.AuthProperties;
import clientprocessing.authservice.config.properties.RabbitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({AuthProperties.class, RabbitProperties.class})
@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
