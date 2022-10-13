package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository <Customer, Integer> {
}
