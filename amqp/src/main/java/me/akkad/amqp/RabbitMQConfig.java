package me.akkad.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class RabbitMQConfig {
    @Bean
    public CachingConnectionFactory connectionFactory() {
        // Configure and return the ConnectionFactory
        // Set the host, port, username, password, and virtual host properties
        // Return an instance of the appropriate ConnectionFactory implementation
        // (e.g., CachingConnectionFactory or SimpleRoutingConnectionFactory)
        // Example:
        // CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // connectionFactory.setHost("localhost");
        // connectionFactory.setPort(5672);
        // connectionFactory.setUsername("guest");
        // connectionFactory.setPassword("guest");
        // connectionFactory.setVirtualHost("/");
        // return connectionFactory;
        return new CachingConnectionFactory();
    }

    /**
     * <strong>Message Producer:</strong>
     * To send messages to RabbitMQ, you need a message producer.
     * In Spring Boot, you can create a producer by implementing
     * the {@link RabbitTemplate} class provided by the Spring AMQP library.
     * The {@link RabbitTemplate} simplifies message publishing by providing convenient
     * methods to send messages to specific exchanges and queues.
     *
     * @return {@link AmqpTemplate}
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    /**
     * <strong>Message Serialization</strong>
     * <p>
     * RabbitMQ requires messages to be serialized before sending and deserialized upon receiving
     * Spring Boot handles message serialization and deserialization automatically using the configured
     * message converter. By default, it uses a SimpleMessageConverter, but you can customize it to use
     * JSON, XML, or other formats.
     * </p>
     * @return {@link MessageConverter}
     */
    @Bean
    public MessageConverter messageConverter() {
        // Configure and return the MessageConverter
        // Set the appropriate converter implementation for message serialization
        // (e.g., Jackson2JsonMessageConverter for JSON serialization)
        return new Jackson2JsonMessageConverter();
    }

    /**
     * <strong>Message Consumer</strong>
     * <p>
     * To receive and process messages from RabbitMQ, you need a message consumer.
     * In Spring Boot, you can create a consumer by using the @RabbitListener annotation on a method.
     * This annotation tells Spring Boot to listen for messages from a specific queue and invokes
     * the annotated method whenever a message arrives.
     * </p>
     * @return {@link SimpleRabbitListenerContainerFactory}
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(messageConverter());
        return factory;
    }
}
