package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepo businessRepo;

    @Autowired
    public BusinessService(BusinessRepo businessRepo) {
        this.businessRepo = businessRepo;
    }

    public List<Business> getAllBusinesses() {
        return businessRepo.findAll();
    }

    public Optional<Business> getBusinessById(int businessId) {
        return businessRepo.findById(businessId);
    }

    public Business addBusiness(Business business) {
        return businessRepo.save(business);
    }

    public void updateBusiness(int businessId, Business business) {
        businessRepo.findById(businessId)
                .ifPresent(existingBusiness -> {
                    business.setBusinessId(businessId);
                    businessRepo.save(business);
                });
    }

    public void deleteBusiness(int businessId) {
        businessRepo.findById(businessId)
                .ifPresent(business -> businessRepo.deleteById(businessId));
    }
}
