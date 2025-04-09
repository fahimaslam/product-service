package com.sparkkees.pk.product_service.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name:orderQueue}")
    public String QUEUE_NAME = "orderQueue";
    @Value("${rabbitmq.exchange.name:myExchange}")
    public String EXCHANGE_NAME = "myExchange";
    @Value("${rabbitmq.routing.key:myRoutingKey}")
    public String ROUTING_KEY = "myRoutingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}