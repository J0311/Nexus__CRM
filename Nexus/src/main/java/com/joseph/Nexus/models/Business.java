package com.joseph.Nexus.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "business")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Business{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id", nullable = false)
    private int businessId;

    @Column(nullable = false, columnDefinition = "VARCHAR(256)")
    private String business_name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(256)")
    private String email;

    @Column(unique = true, nullable = false, columnDefinition = "INTEGER")
    private int phone_number;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isPending;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contractId", referencedColumnName = "contract_id")
    private Contract contract;

    public Business(String business_name, String email){
        this.business_name = business_name;
        this.email = email;
    }

}
