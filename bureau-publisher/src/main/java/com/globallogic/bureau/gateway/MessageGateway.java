package com.globallogic.bureau.gateway;

import com.globallogic.bureau.dto.BureauMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import static com.globallogic.bureau.constants.ApplicationConstants.REQUEST_CHANNEL_NAME;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = REQUEST_CHANNEL_NAME)
    void generate(BureauMessage message);

}