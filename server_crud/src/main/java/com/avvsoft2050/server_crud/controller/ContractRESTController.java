package com.avvsoft2050.server_crud.controller;

import com.avvsoft2050.server_crud.entity.Contract;
import com.avvsoft2050.server_crud.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContractRESTController {

    private final ContractService contractService;

    public ContractRESTController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contracts")
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PostMapping("/contracts")
    public Contract addNewContract(@RequestBody Contract contract) {
        contractService.saveContract(contract);
        return contract;
    }

    @PutMapping("/contracts")
    public Contract updateContract(@RequestBody Contract contract) {
        contractService.saveContract(contract);
        return contract;
    }

    @GetMapping("/contracts/{id}")
    public Contract getContract(@PathVariable Integer id) {
        return contractService.getContract(id);
    }

    @DeleteMapping("/contracts/{id}")
    public void deleteContract(@PathVariable Integer id) {
        contractService.deleteContract(id);
    }
}
