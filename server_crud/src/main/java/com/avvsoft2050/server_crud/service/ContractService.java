package com.avvsoft2050.server_crud.service;

import com.avvsoft2050.server_crud.entity.Contract;
import java.util.List;

public interface ContractService {
    List<Contract> getAllContracts();
    void saveContract(Contract contract);
    Contract getContract(Integer id);
    void deleteContract(Integer id);
}
