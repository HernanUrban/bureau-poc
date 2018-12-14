package com.globallogic.bureau.service;

import com.globallogic.bureau.dto.BureauMessage;
import com.globallogic.bureau.gateway.MessageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private MessageGateway messageGateway;

    public BureauMessage publish(BureauMessage message) {
        log.info("Sending message - id: {}, cuit: {}, payload: {}", message.getId(), message.getCuit(), message.getPayload());
        messageGateway.generate(message);
        return message;
    }
}
