package com.avvsoft2050.client_fx_crud.services;

import com.avvsoft2050.client_fx_crud.pojo.Contract;
import com.avvsoft2050.client_fx_crud.pojo.ContractForTable;
import javafx.scene.control.*;

public interface ServiceContract {
    void showAllContracts(TableView<ContractForTable> tableView,
                          TableColumn<ContractForTable, String> colContractDate,
                          TableColumn<ContractForTable, String> colContractNumber,
                          TableColumn<ContractForTable, String> colContractUpdate,
                          TableColumn<ContractForTable, CheckBox> colStatus);
    void saveContract(Contract contract);

    ContractForTable getSelectedContract(TableView<ContractForTable> tableView);

    void deleteContract(int id);

    void showContractDetails(
            ContractForTable contractForTable,
            TextField tfId,
            DatePicker dPickerDate,
            TextField tfContractNumber,
            DatePicker dPickerUpdate);

}
