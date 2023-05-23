package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepo contractRepo;

    @Autowired
    public ContractService(ContractRepo contractRepo) {
        this.contractRepo = contractRepo;
    }

    public List<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    public Optional<Contract> getContractById(int id) {
        return contractRepo.findById(id);
    }

    public Contract addContract(Contract contract) {
        return contractRepo.save(contract);
    }

    public void updateContract(int contractId, Contract contract) {
        Optional<Contract> existingContract = contractRepo.findById(contractId);
        if (existingContract.isPresent()) {
            contract.setContractId(contractId);
            contractRepo.save(contract);
        }
    }

    public void deleteContract(int id) {
        contractRepo.deleteById(id);
    }
}
