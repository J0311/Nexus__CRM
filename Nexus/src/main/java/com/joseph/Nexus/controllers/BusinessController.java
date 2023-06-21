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

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Business> getBusinessById(@PathVariable int businessId) {
        Optional<Business> business = businessService.getBusinessById(businessId);
        return business.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Business newBusiness = businessService.addBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBusiness);
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<Void> updateBusiness(@PathVariable int businessId, @RequestBody Business business) {
        businessService.updateBusiness(businessId, business);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{businessId}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable int businessId) {
        businessService.deleteBusiness(businessId);
        return ResponseEntity.noContent().build();
    }
}
