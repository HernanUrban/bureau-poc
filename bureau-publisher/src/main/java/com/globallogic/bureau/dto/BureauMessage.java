package com.globallogic.bureau.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class BureauMessage {


    private String id;
    private String cuit;
    private Map<String, String> payload;
}
