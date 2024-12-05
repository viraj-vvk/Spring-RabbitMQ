package com.example.demo.config;

import com.example.demo.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "queueOne")
    public void queueOneListener(Message message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "queueTwo")
    public void queueTwoListener(Message message) {
        System.out.println(message);
    }
}
