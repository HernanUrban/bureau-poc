package com.globallogic.bureau.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Value("${rabbit.exchange.name:bureau.exchange}")
    private String exchangeName;

    @Bean
    TopicExchange worksExchange() {
        return new TopicExchange(exchangeName, true, false);
    }

    @Bean
    public RabbitTemplate worksRabbitTemplate() {
        RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
        r.setExchange(exchangeName);
        r.setRoutingKey("bureau");
        r.setConnectionFactory(rabbitConnectionFactory);
        return r;
    }

}