package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepo contractRepository;

    @Autowired
    public ContractService(ContractRepo contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(int id) {
        return contractRepository.findById(id);
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }
}
