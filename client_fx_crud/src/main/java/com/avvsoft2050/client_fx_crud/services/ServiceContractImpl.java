package com.avvsoft2050.client_fx_crud.services;

import com.avvsoft2050.client_fx_crud.Communication;
import com.avvsoft2050.client_fx_crud.pojo.Contract;
import com.avvsoft2050.client_fx_crud.pojo.ContractForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceContractImpl implements ServiceContract {

    private final Communication communication;

    public ServiceContractImpl(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void showAllContracts(TableView<ContractForTable> tableView,
                                 TableColumn<ContractForTable, String> colContractDate,
                                 TableColumn<ContractForTable, String> colContractNumber,
                                 TableColumn<ContractForTable, String> colContractUpdate,
                                 TableColumn<ContractForTable, CheckBox> colStatus) {
        List<Contract> allContracts;
        try {
            allContracts = communication.getAllContracts();
            List<ContractForTable> allContractsForTable = new ArrayList<>();
            for (Contract contract : allContracts) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(contractIsActive(contract));
                allContractsForTable.add(new ContractForTable(
                        contract.getContractId(),
                        contract.getContractDate(),
                        contract.getContractNumber(),
                        contract.getContractUpdate(),
                        checkBox));
            }
            ObservableList<ContractForTable> contractsObserve = FXCollections.observableArrayList(allContractsForTable);
            colContractDate.setCellValueFactory(new PropertyValueFactory<>("contractDate"));
            colContractNumber.setCellValueFactory(new PropertyValueFactory<>("contractNumber"));
            colContractUpdate.setCellValueFactory(new PropertyValueFactory<>("contractUpdate"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            tableView.setItems(contractsObserve);
        } catch (Exception e) {
            // TODO Error window
        }
    }

    @Override
    public void saveContract(Contract contract) {
        communication.saveContract(contract);
    }

    @Override
    public ContractForTable getSelectedContract(TableView<ContractForTable> tableView) {
        return tableView.getFocusModel().getFocusedItem();
    }

    @Override
    public void deleteContract(int id) {
        communication.deleteContract(id);
    }

    @Override
    public void showContractDetails(
            ContractForTable contractForTable,
            TextField tfId,
            DatePicker dPickerDate,
            TextField tfContractNumber,
            DatePicker dPickerUpdate) {
        tfId.setText(contractForTable.getContractId());
        dPickerDate.setValue(LocalDate.parse(contractForTable.getContractDate()));
        tfContractNumber.setText(contractForTable.getContractNumber());
        dPickerUpdate.setValue(LocalDate.parse(contractForTable.getContractUpdate()));
    }

    private boolean contractIsActive(Contract contract) {
        LocalDate today = LocalDate.now();
        LocalDate contractUpdate = contract.getContractUpdate();
        return contractUpdate.isAfter(today.minusDays(60));
    }
}
