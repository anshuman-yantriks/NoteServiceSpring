package com.example.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void setupQueueDestinations() {
        Exchange ex = ExchangeBuilder.directExchange("email-data-exchange")
                            .durable(true)
                            .build();
        amqpAdmin.declareExchange(ex);
        Queue q = QueueBuilder.durable("collaboratorEmail")
                            .build();
        amqpAdmin.declareQueue(q);
        Binding b = BindingBuilder.bind(q)
                            .to(ex)
                            .with("collaboratorEmail")
                            .noargs();
        amqpAdmin.declareBinding(b);
    }
}