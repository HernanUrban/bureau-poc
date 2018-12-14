package com.globallogic.bureau.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder(builderClassName = "Bulider")
public class BureauResponse implements Serializable {

    private String id;
    private String cuit;
    private Map<String, String> fuentes;


}
