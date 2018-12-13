package com.globallogic.bureau.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

import static com.globallogic.bureau.constants.ApplicationConstants.REQUEST_CHANNEL_NAME;

@Configuration
public class BureauMessageOutbound {

        @Autowired
        private RabbitConfig rabbitConfig;

        @Bean
        public IntegrationFlow toOutboundQueueFlow() {
            return IntegrationFlows.from(REQUEST_CHANNEL_NAME)
                    .transform(Transformers.toJson())
                    .handle(Amqp.outboundAdapter(rabbitConfig.worksRabbitTemplate()))
                    .get();
        }

    }
