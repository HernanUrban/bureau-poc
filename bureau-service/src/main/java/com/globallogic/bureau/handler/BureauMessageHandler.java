package com.globallogic.bureau.handler;

import com.globallogic.bureau.dto.BureauMessage;
import com.globallogic.bureau.dto.BureauResponse;
import com.globallogic.bureau.service.BureauMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BureauMessageHandler {

    @Autowired
    private BureauMessageService bureauMessageService;

    public void process(BureauMessage message) {
        log.info("Handling bureau message - id: {}, cuit: {}, payload: {}", message.getId(), message.getCuit(), message.getPayload());
        BureauResponse response = bureauMessageService.getNosisResponse(message.getId(), message.getCuit(), message.getPayload());

    }

}
