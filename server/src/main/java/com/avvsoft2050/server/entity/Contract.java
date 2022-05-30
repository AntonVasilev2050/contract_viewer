package com.avvsoft2050.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private int contractId;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "contract_number")
    private int contractNumber;

    @Column(name = "contract_update")
    private LocalDate contractUpdate;
}
