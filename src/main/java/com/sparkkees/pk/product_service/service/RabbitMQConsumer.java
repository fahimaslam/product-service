package com.sparkkees.pk.product_service.service;

import com.sparkkees.pk.product_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queue.name:orderQueue}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}

