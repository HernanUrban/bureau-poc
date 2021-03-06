package com.globallogic.bureau.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class BureauMessage implements Serializable {

    private String id;
    private String cuit;
    private Map<String, String> payload;
}
