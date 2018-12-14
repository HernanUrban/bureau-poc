package com.globallogic.bureau.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.exchange.name:bureau.exchange}")
    private String exchangeName;

    @Value("${rabbit.exchange.dl.name:bureau.exchange.dl}")
    private String exchangeDeadLeatterName;

    @Value("${rabbit.queue.name:bureau.queue}")
    private String queueName;

    @Value("${rabbit.queue.dl.name:bureau.queue.dl}")
    private String DLqueueName;

    @Value("${rabbit.routing.key:bureau}")
    private String routingKey;

    @Value("${rabbit.concurrent.consumers:1}")
    private Integer consumers;

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    Exchange bureauExchange() {
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    @Bean
    public Queue bureauQueue() {
        return QueueBuilder.durable(queueName)
                .withArgument("x-dead-letter-exchange", bureauDlExchange().getName())
                .build();
    }

    @Bean
    Binding bureauBinding() {
        return BindingBuilder
                .bind(bureauQueue())
                .to(bureauExchange()).with(routingKey).noargs();
    }

    @Bean
    Exchange bureauDlExchange() {
        return ExchangeBuilder
                .topicExchange(exchangeDeadLeatterName)
                .durable(true)
                .build();
    }

    //Queue to hold Deadletter messages from bureauQueue
    @Bean
    public Queue bureauDLQueue() {
        return QueueBuilder
                .durable(DLqueueName)
                .withArgument("x-message-ttl", 20000)
                .withArgument("x-dead-letter-exchange", bureauExchange().getName())
                .build();
    }

    @Bean
    Binding bureauDlBinding() {
        return BindingBuilder
                .bind(bureauDLQueue())
                .to(bureauDlExchange()).with(routingKey)
                .noargs();
    }


    @Bean
    public SimpleMessageListenerContainer bureauListenerContainer() {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(rabbitConnectionFactory);
        container.setQueues(bureauQueue());
        container.setConcurrentConsumers(consumers);
        container.setDefaultRequeueRejected(false);
        return container;
    }

    @Bean
    RetryOperationsInterceptor interceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .backOffOptions(1000, 3, 60000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }
}