package com.avvsoft2050.client_fx_table_view.controllersFX;

import com.avvsoft2050.client_fx_table_view.Communication;
import com.avvsoft2050.client_fx_table_view.pojo.Contract;
import com.avvsoft2050.client_fx_table_view.pojo.ContractForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/contract_viewer_main.fxml")
public class ControllerFXContract implements Initializable {

    public TableView<ContractForTable> tableView;
    public TableColumn<ContractForTable, String> colContractDate;
    public TableColumn<ContractForTable, String> colContractNumber;
    public TableColumn<ContractForTable, String> colContractUpdate;
    public TableColumn<ContractForTable, CheckBox> colStatus;

    private final Communication communication;
    public ControllerFXContract(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Contract> allContracts;
        allContracts = communication.getAllContracts();
        List<ContractForTable> allContractsForTable = new ArrayList<>();
        for(Contract contract : allContracts){
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(contractIsActive(contract));
            allContractsForTable.add(new ContractForTable(
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
    }

    private boolean contractIsActive(Contract contract) {
        LocalDate today = LocalDate.now();
        LocalDate contractUpdate = contract.getContractUpdate();
        return contractUpdate.isAfter(today.minusDays(60));
    }
}
