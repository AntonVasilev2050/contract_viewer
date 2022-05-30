package com.avvsoft2050.client_fx.controllersFX;

import com.avvsoft2050.client_fx.Communication;
import com.avvsoft2050.client_fx.pojo.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/contract_viewer_main.fxml")
public class ControllerFXContract implements Initializable {

    public VBox vBoxContracts;
    public ListView<Contract> listViewContracts;

    private final Communication communication;

    @Autowired
    public ControllerFXContract(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Contract> allContracts;
        try {
            allContracts = communication.getAllContracts();
            ObservableList<Contract> contractsObserve = FXCollections.observableList(allContracts);
            listViewContracts.setItems(contractsObserve);
            listViewContracts.setCellFactory(new Callback<ListView<Contract>, ListCell<Contract>>() {
                @Override
                public ListCell<Contract> call(ListView<Contract> param) {
                    return new ListCell<Contract>() {
                        @Override
                        public void updateItem(Contract contract, boolean empty) {
                            super.updateItem(contract, empty);
                            if (contract != null) {
                                CheckBox checkBox = new CheckBox(
                                                contract.getContractDate() + "                " +
                                                contract.getContractNumber() + "                            " +
                                                contract.getContractUpdate() + "                   ");
                                checkBox.setSelected(contractIsActive(contract));
//                                This makes the checkBox not change when clicked
                                checkBox.setOnMouseClicked(event -> {
                                    checkBox.setSelected(contractIsActive(contract));
                                });
                                setGraphic(checkBox);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            // toDo the message screen
        }

    }

    private boolean contractIsActive(Contract contract) {
        LocalDate today = LocalDate.now();
        LocalDate contractUpdate = contract.getContractUpdate();
        return contractUpdate.isAfter(today.minusDays(60));
    }
}
