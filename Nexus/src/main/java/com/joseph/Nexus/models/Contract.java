package com.joseph.Nexus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isPending;

    public Contract (String client_name, boolean isPending){
        this.client_name = client_name;
        this.isPending = isPending;
    }
}
