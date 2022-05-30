package com.avvsoft2050.server.controller;

import com.avvsoft2050.server.entity.Contract;
import com.avvsoft2050.server.service.ContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContractRESTController {

    private final ContractService contractService;

    public ContractRESTController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contracts")
    public List<Contract> getAllContracts(){
        return contractService.getAllContracts();
    }
}
