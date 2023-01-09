package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {

    BusinessService businessService;

    @Autowired
    public BusinessController (BusinessService businessService){
        this.businessService = businessService;
    }

    @GetMapping
    public String getTest(){
        return businessService.test();
    }


}
