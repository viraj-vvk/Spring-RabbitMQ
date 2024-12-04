package com.example.demo.model;

record Message(String key, String value) {
}

public record SimpleMessage(String routingKey, String exchange, Message message) {
}