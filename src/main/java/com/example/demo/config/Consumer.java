package com.example.demo.config;

import com.example.demo.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "qone")
    public void queueOneListener(Message message) {
        System.out.printf("Message from queueOne: %s", message);
    }

    @RabbitListener(queues = "qtwo")
    public void queueTwoListener(Message message) {
        System.out.printf("Message from queueTwo: %s", message);
    }

    @RabbitListener(queues = "qthree")
    public void queueThreeListener(Message message) {
        System.out.printf("Message from queueThree: %s", message);
    }
}
