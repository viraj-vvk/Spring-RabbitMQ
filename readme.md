- docker start command: docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:4.0.4-management

- directExchange request:
  - {
    "routingKey": "direct-exchange-key",
    "exchange": "directExchange-one",
    "message": {
    "key": "m-1",
    "value": "message one"
    }
    }
- topicExchange request:
  - {
    "routingKey": "kone.ktwo.kthree",
    "exchange": "topic:exchange",
    "message": {
    "key": "m-1",
    "value": "message one"
    }
    }
  - only queueTwo and queueThree will receive message