package me.akkad.notification;

import me.akkad.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "me.akkad.notification",
                "me.akkad.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer mqMessageProducer,
            NotificationConfig notificationConfig
    ) {

        return args -> {
            mqMessageProducer.publish(
                    new Person("Ali",19),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationKey()
            );
        };
    }
    record Person(String name,int age){}
}
