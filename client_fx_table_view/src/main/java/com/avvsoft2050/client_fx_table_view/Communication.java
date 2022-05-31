package com.avvsoft2050.client_fx_table_view;

import com.avvsoft2050.client_fx_table_view.pojo.Contract;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final String URL = "http://localhost:8080/api/v1/contracts";
    private final RestTemplate restTemplate;

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Contract> getAllContracts(){

        ResponseEntity<List<Contract>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Contract>>() {});
        return responseEntity.getBody();
    }
}
