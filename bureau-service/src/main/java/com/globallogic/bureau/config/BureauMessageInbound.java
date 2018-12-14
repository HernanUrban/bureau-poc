package com.globallogic.bureau.config;

import com.globallogic.bureau.dto.BureauMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

@Configuration
public class BureauMessageInbound {

    @Autowired
    private SimpleMessageListenerContainer bureauListenerContainer;

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(bureauListenerContainer))
                .transform(Transformers.fromJson(BureauMessage.class))
                .handle("bureauMessageHandler", "process")
                .get();
    }

}
