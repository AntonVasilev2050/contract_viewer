package com.avvsoft2050.client_fx_table_view.pojo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;

public class ContractForTable {

    private final SimpleStringProperty contractDate;
    private final SimpleStringProperty contractNumber;
    private final SimpleStringProperty contractUpdate;
    private CheckBox status;


    public ContractForTable(LocalDate contractDate,
                            Integer contractNumber,
                            LocalDate contractUpdate,
                            CheckBox checkBox) {
        this.contractDate = new SimpleStringProperty(contractDate.toString());
        this.contractNumber = new SimpleStringProperty(contractNumber.toString());
        this.contractUpdate = new SimpleStringProperty(contractUpdate.toString());
        this.status = checkBox;
    }

    public String getContractDate() {
        return contractDate.get();
    }

    public void setContractDate(String contractDate) {
        this.contractDate.set(contractDate);
    }

    public String getContractNumber() {
        return contractNumber.get();
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber.set(contractNumber);
    }

    public String getContractUpdate() {
        return contractUpdate.get();
    }

    public void setContractUpdate(String contractUpdate) {
        this.contractUpdate.set(contractUpdate);
    }

    public CheckBox getStatus() {
        return status;
    }

    public void setStatus(CheckBox status) {
        this.status = status;
    }
}
