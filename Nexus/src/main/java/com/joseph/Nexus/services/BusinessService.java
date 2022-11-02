package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    BusinessRepo businessRepo;

    @Autowired
    public BusinessService(BusinessRepo businessRepo){
        this.businessRepo = businessRepo;
    }

    public String getBusinesses() {
        return "Business Test in progress";
    }
}
