package com.avvsoft2050.server_crud.service;

import com.avvsoft2050.server_crud.dao.ContractRepository;
import com.avvsoft2050.server_crud.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void saveContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public Contract getContract(Integer id) {
        Contract contract = null;
        Optional<Contract> optional = contractRepository.findById(id);
        if(optional.isPresent()){
            contract = optional.get();
        }
        return contract;
    }

    @Override
    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}
