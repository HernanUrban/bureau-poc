package com.globallogic.bureau.service;

import com.globallogic.bureau.dto.BureauMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BureauMessageHandler {

    public void process(BureauMessage message) {
        log.info("Handling bureau message - id: {}, payload: {}", message.getId(), message.getPayload());
    }

}
