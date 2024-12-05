package com.example.demo.controller;

import com.example.demo.model.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RabbitTemplate template;

    @Autowired
    public MessageController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping
    public String getData(@RequestBody SimpleMessage messageBody) {
        template.convertAndSend(messageBody.exchange(), messageBody.routingKey(), messageBody.message());
        return "Success";
    }
}

