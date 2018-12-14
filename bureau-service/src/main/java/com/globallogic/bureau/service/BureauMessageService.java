package com.globallogic.bureau.service;

import com.globallogic.bureau.dto.BureauResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BureauMessageService {

    @Cacheable(value = "nosis", key = "#cuit")
    public BureauResponse getNosisResponse(String id, String cuit, Map<String, String> payload) {
        //TODO integrate with Nosis
        log.info("Accessing Nosis for id: {}, cuit: {}", id, cuit);
        return createMock(id, cuit, payload);
    }

    //Mock
    private BureauResponse createMock(String id, String cuit, Map<String, String> payload) {
        Map<String, String> nosisValues = new HashMap<>();
        nosisValues.put("1","0");
        nosisValues.put("2","0");
        nosisValues.put("3","0");
        nosisValues.put("4","0");
        nosisValues.put("5","0");
        nosisValues.put("6","0");
        nosisValues.put("7","0");
        nosisValues.put("8","0");
        return BureauResponse.builder()
                .cuit(cuit)
                .fuentes(nosisValues)
                .id(id)
                .build();
    }

}
