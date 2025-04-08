package com.sparkkees.pk.product_service.controller;

import com.sparkkees.pk.product_service.service.RabbitMQProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class MessageController {

    private final RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}

