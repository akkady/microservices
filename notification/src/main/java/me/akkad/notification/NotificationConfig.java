package me.akkad.notification;

import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQues;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationKey;

    @Bean
    public Exchange internalTopicExchange() {
        // Configure and return the Exchange
        // Create an instance of the appropriate Exchange implementation
        // (e.g., DirectExchange, TopicExchange, FanoutExchange)
        // Set the exchange properties (e.g., name, durability, auto-delete)
        // Example:
        // return new DirectExchange("exchangeName");
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        // Configure and return the Queue
        // Create an instance of the Queue
        // Set the queue properties (e.g., name, durability, auto-delete)
        // Example:
        // return new Queue("myQueue");
        return new Queue(this.notificationQues);
    }

    @Bean
    public Binding binding() {
        // Configure and return the Binding
        // Create an instance of the Binding
        // Set the binding properties (e.g., routing key)
        // Example:
        // return BindingBuilder.bind(queue).to(exchange).with("myRoutingKey");
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationKey)
                .noargs();
    }
}
