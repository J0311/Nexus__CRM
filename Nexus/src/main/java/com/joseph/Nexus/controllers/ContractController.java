package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("")
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Contract> getContractById(@PathVariable int contractId) {
        Optional<Contract> contract = contractService.getContractById(contractId);
        if (contract.isPresent()) {
            return new ResponseEntity<>(contract.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        Contract newContract = contractService.addContract(contract);
        return new ResponseEntity<>(newContract, HttpStatus.CREATED);
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<Void> updateContract(@PathVariable int contractId, @RequestBody Contract contract) {
        contractService.updateContract(contractId, contract);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable int contractId) {
        contractService.deleteContract(contractId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
