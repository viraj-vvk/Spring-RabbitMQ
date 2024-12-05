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
        return new Queue("qone");
    }

    @Bean
    public Queue queueTwo() {
        return new Queue("qtwo");
    }

    @Bean
    public Queue queueThree() {
        return new Queue("qthree");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topic:exchange");
    }

    @Bean
    public Binding bindingOne(Queue queueOne, TopicExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange).with("*.kone.*");
    }

    @Bean
    public Binding bindingTwo(Queue queueTwo, TopicExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange).with("*.ktwo.*");
    }

    @Bean
    public Binding bindingThree(Queue queueThree, TopicExchange exchange) {
        return BindingBuilder.bind(queueThree).to(exchange).with("#.kthree");
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
