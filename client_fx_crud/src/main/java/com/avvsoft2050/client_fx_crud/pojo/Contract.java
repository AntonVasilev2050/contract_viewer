package com.avvsoft2050.client_fx_crud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contract {

    private int contractId;
    private LocalDate contractDate;
    private int contractNumber;
    private LocalDate contractUpdate;
}
