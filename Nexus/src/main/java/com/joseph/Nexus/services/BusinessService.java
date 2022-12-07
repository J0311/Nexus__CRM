package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

}
