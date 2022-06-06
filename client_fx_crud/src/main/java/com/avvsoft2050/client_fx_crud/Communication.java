package com.avvsoft2050.client_fx_crud;

import com.avvsoft2050.client_fx_crud.pojo.Contract;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;

@Component
public class Communication {

    private final String URL = "http://localhost:8080/api/v1/contracts";
    private final RestTemplate restTemplate;

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Contract> getAllContracts() {

        ResponseEntity<List<Contract>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Contract>>() {
                });
        List<Contract> list = Objects.requireNonNull(responseEntity.getBody());
        return list;
    }

    public Contract getContract(int id) {
        Contract contract = restTemplate.getForObject(URL + "/" + id, Contract.class);
        return contract;
    }

    public void saveContract(Contract contract) {
        int id = contract.getContractId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, contract, String.class);
        } else {
            restTemplate.put(URL, contract);
        }
    }

    public void deleteContract(int id) {
        restTemplate.delete(URL + "/" + id, Contract.class);
    }
}
