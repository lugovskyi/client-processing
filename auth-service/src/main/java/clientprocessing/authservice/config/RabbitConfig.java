package clientprocessing.authservice.config;

import clientprocessing.authservice.AuthServiceApplication;
import clientprocessing.authservice.config.properties.RabbitProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConditionalOnClass(AuthServiceApplication.class)
public class RabbitConfig {
    private final RabbitProperties properties;

    @Autowired
    public RabbitConfig(RabbitProperties properties) {
        this.properties = properties;
    }

    @Bean
    Queue queue() {
        Map<String, Object> props = Map.of("x-dead-letter-exchange", properties.deadExchange());
        return new Queue(properties.queue(), true, false, false, props);
    }

    @Bean
    Queue deadQueue() {
        return new Queue(properties.deadQueue(), true, false, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(properties.exchange());
    }

    @Bean
    FanoutExchange deadExchange() {
        return new FanoutExchange(properties.deadExchange());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.routingKey());
    }

    @Bean
    Binding deadBinding(Queue deadQueue, FanoutExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setExchange(properties.exchange());
        template.setRoutingKey(properties.routingKey());
        return template;
    }
}
