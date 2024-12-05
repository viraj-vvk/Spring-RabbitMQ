package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queueOne() {
        return new Queue("queueOne");
    }

    @Bean
    public Queue queueTwo() {
        return new Queue("queueTwo");
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange("fanout:exchange");
    }

    @Bean
    public Binding bindingOne(Queue queueOne, FanoutExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange);
    }

    @Bean
    public Binding bindingTwo(Queue queueTwo, FanoutExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        return template;
    }
}
