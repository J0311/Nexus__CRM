package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    BusinessRepo businessRepo;

    @Autowired
    public BusinessService(BusinessRepo businessRepo){
        this.businessRepo = businessRepo;
    }

    public String test() {
        return "Business Test in progress";
    }
    /**
     * Returns a list of all businesses in the database. If no business entity exist, an
     * empty list is returned.
     * @return a list of all businesses in the database
     */

    public List<Business> findAll() {
        return businessRepo.findAll();
    }

    /**
     * Returns the business with the given business id if it exists in the database.
     *
     * @param business_Id - the id of the business to retrieve
     * @return an optional containing the business with the given id if it exists
     */

    public Optional<Business> findById(int business_Id) {
        return businessRepo.findById(business_Id);
    }

    /**
     * Saves the given business to the database.
     *
     * @param business - the business to save
     * @return the saved business
     */

    public Business save(Business business) {
        return businessRepo.save(business);
    }

    /**
     * Deletes the business with the given id from the database.
     *
     * @param business_Id - the id of the business to delete
     */

    public void delete(int business_Id) {
        businessRepo.deleteById(business_Id);
    }

    /**
     * Returns a list of businesses with the given keyword in the name.
     *
     * @param keyword - the keyword to search for
     * @return a list of businesses with the given keyword in the name
     */
    public List<Business> searchByName(String keyword) {
        return businessRepo.searchByName(keyword);
    }

}
