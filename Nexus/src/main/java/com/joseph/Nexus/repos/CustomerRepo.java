package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository <Customer, Integer> {

    /**
     * Returns a list of all customers in the database that are associated
     * with the customer with the given id.
     *
     * @param customerId - the id of the customer to get information of
     * @return a list of all customers associated with the given id
     */

    @Query(value = "SELECT * FROM customer WHERE customer_id = :customerId", nativeQuery = true)
    List<Customer> findAllByCustomerId(@Param("customerId") int customerId);
}
