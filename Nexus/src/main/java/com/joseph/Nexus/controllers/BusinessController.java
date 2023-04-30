package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Business> getBusinessById(@PathVariable int businessId) {
        Optional<Business> business = businessService.getBusinessById(businessId);
        if (business.isPresent()) {
            return new ResponseEntity<>(business.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Business newBusiness = businessService.addBusiness(business);
        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<Void> updateBusiness(@PathVariable int businessId, @RequestBody Business business) {
        businessService.updateBusiness(businessId, business);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable int businessId) {
        businessService.deleteBusiness(businessId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
