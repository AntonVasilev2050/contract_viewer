package com.avvsoft2050.client_fx.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class Contract {

    private int contractId;
    private LocalDate contractDate;
    private int contractNumber;
    private LocalDate contractUpdate;
}
