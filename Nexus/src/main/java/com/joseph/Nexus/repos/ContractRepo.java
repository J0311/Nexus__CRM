package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository <Contract, Integer> {
}
