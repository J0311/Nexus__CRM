package com.joseph.Nexus.models;


import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id", nullable = false)
    private int contractId;

    @Column(nullable = false, columnDefinition = "VARCHAR(256)")
    private String client_name;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isPending;

    public Contract (String client_name, boolean isPending){
        this.client_name = client_name;
        this.isPending = isPending;
    }

    public Contract(int contractId, String client_name) {
        this.contractId = contractId;
        this.client_name = client_name;
    }
}
