package com.example.demo.model;

public record SimpleMessage(String routingKey, String exchange, Message message) {
}