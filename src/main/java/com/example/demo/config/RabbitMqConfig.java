package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

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
    public Queue queueThree() {
        return new Queue("queueThree");
    }

    @Bean
    public HeadersExchange exchange() {
        return new HeadersExchange("header:exchange");
    }

    @Bean
    public Binding bindingOne(Queue queueOne, HeadersExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange).whereAll(Map.of("value1", "Java", "value2", "javascript")).match();
    }

    @Bean
    public Binding bindingTwo(Queue queueTwo, HeadersExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange).whereAny(Map.of("value1", "python", "value2", "javascript")).match();
    }

    @Bean
    public Binding bindingThree(Queue queueThree, HeadersExchange exchange) {
        return BindingBuilder.bind(queueThree).to(exchange).whereAll(Map.of("value1", "javascript", "value2", "Java")).match();
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
