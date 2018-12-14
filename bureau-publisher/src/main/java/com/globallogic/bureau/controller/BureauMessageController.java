package com.globallogic.bureau.controller;

import com.globallogic.bureau.dto.BureauMessage;
import com.globallogic.bureau.gateway.MessageGateway;
import com.globallogic.bureau.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bureau")
public class  BureauMessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    @ResponseBody
    public BureauMessage generateMessage(@RequestBody BureauMessage message) {
        return messageService.publish(message);
    }
}
