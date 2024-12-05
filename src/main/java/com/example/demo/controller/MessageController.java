package com.example.demo.controller;

import com.example.demo.model.SimpleMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private RabbitTemplate template;

    @PostMapping
    public String getData(@RequestBody SimpleMessage messageBody) {
        MessageProperties properties = new MessageProperties();
        properties.setHeaders(Map.of("value1", "Java", "value2", "javascript"));

        Message message = messageConverter.toMessage(messageBody.message(), properties);

        template.convertAndSend(messageBody.exchange(), "", message);
        return "Success";
    }
}

