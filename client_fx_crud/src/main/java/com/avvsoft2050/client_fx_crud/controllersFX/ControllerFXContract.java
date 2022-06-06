package com.avvsoft2050.client_fx_crud.controllersFX;

import com.avvsoft2050.client_fx_crud.pojo.Contract;
import com.avvsoft2050.client_fx_crud.pojo.ContractForTable;
import com.avvsoft2050.client_fx_crud.services.ServiceContract;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
@FxmlView("/contract_viewer_main.fxml")
public class ControllerFXContract implements Initializable {

    public TableView<ContractForTable> tableView;
    public TableColumn<ContractForTable, String> colContractDate;
    public TableColumn<ContractForTable, String> colContractNumber;
    public TableColumn<ContractForTable, String> colContractUpdate;
    public TableColumn<ContractForTable, CheckBox> colStatus;
    public TextField tfId;
    public DatePicker dPickerDate;
    public TextField tfContractNumber;
    public DatePicker dPickerUpdate;
    public Button buttonContracts;
    public Button buttonAddContract;
    public Button buttonUpdateContract;
    public Button buttonDeleteContract;

    private final ServiceContract serviceContract;

    public ControllerFXContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceContract.showAllContracts(tableView, colContractDate, colContractNumber, colContractUpdate, colStatus);
    }

    public void buttonContractsClicked(ActionEvent actionEvent) {
        serviceContract.showAllContracts(tableView, colContractDate, colContractNumber, colContractUpdate, colStatus);
    }

    public void buttonAddContractClicked(ActionEvent actionEvent) {
        saveOrUpdateContract(0);
    }

    public void buttonUpdateContactClicked(ActionEvent actionEvent) {
        saveOrUpdateContract(Integer.parseInt(tfId.getText()));
    }

    public void buttonDeleteContactClicked(ActionEvent actionEvent) {
        int id = Integer.parseInt(tfId.getText());
        serviceContract.deleteContract(id);
        serviceContract.showAllContracts(tableView, colContractDate, colContractNumber, colContractUpdate, colStatus);
    }

    private void saveOrUpdateContract(int id) {
        Contract contract = new Contract(
                id,
                LocalDate.parse(dPickerDate.getValue().toString()),
                Integer.parseInt(tfContractNumber.getText()),
                LocalDate.parse(dPickerUpdate.getValue().toString()));
        serviceContract.saveContract(contract);
        serviceContract.showAllContracts(tableView, colContractDate, colContractNumber, colContractUpdate, colStatus);
    }

    public void tableViewRowClicked(MouseEvent mouseEvent) {
        ContractForTable contractForTable = serviceContract.getSelectedContract(tableView);
        tfId.setText(contractForTable.getContractId());
        dPickerDate.setValue(LocalDate.parse(contractForTable.getContractDate()));
        tfContractNumber.setText(contractForTable.getContractNumber());
        dPickerUpdate.setValue(LocalDate.parse(contractForTable.getContractUpdate()));
    }
}
