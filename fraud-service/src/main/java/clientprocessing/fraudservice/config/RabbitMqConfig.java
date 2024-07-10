package clientprocessing.fraudservice.config;

import clientprocessing.fraudservice.config.properties.RabbitMqProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@EnableRabbit
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitMqProperties properties;

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(properties.host());
        connectionFactory.setUsername(properties.username());
        connectionFactory.setPassword(properties.password());
        connectionFactory.setPort(properties.port());
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory());
        factory.setConcurrentConsumers(properties.consumers());
        factory.setMaxConcurrentConsumers(properties.maxConsumers());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setDefaultRequeueRejected(true);
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
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
    public AmqpTemplate amqpTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setExchange(properties.exchange());
        template.setRoutingKey(properties.routingKey());
        return template;
    }
}
