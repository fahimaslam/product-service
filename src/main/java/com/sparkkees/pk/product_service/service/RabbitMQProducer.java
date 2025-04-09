package com.sparkkees.pk.product_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name:myExchange}")
    private String exchange;
    @Value("${rabbitmq.routing.key:myRoutingKey}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}

