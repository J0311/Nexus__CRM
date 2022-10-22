package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepo extends JpaRepository <Contract, Integer> {

    /**
     * Returns a list of all contracts in the database that are associated
     * with the contract with the given id.
     *
     * @param contractId - the id of the contract
     * @return a list of all contracts associated with the given id
     */

    @Query(value = "SELECT * FROM contract WHERE contract_id = :contractId", nativeQuery = true)
    List<Contract> findAllByContractId(@Param("contractId") int contractId);
}
