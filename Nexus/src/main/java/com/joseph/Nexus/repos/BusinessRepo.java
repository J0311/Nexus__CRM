package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepo extends JpaRepository <Business, Integer> {

    /**
     * Returns a list of all businesses in the database that are associated
     * with the business with the given id.
     *
     * @param businessId - the id of the business to get information of
     * @return a list of all businesses associated with the given id
     */

    @Query(value = "SELECT * FROM product_reviews WHERE business_id = :businessId", nativeQuery = true)
    List<Business> findAllByBusinessId(@Param("businessId") int businessId);

//    List<Business> searchByName(String kw);
    /**
     * Returns a list of all businesses in the database that are associated with the
     * given keyword.
     *
     * @param keyword - the keyword to search for
     * @return a list of all businesses whose name contains the given keyword
     */
//
//    @Query("SELECT p FROM Product p WHERE UPPER(name) LIKE CONCAT('%',UPPER(:keyword),'%')")
//    List<Business> searchByName(@Param("keyword") String keyword);
}
