package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.repos.ContractRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContractServiceTest {

    private ContractService contractService;

    @Mock
    private ContractRepo contractRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contractService = new ContractService(contractRepo);
    }

    @Test
    void getAllContracts_ShouldReturnListOfContracts() {
        // Arrange
        List<Contract> expectedContracts = new ArrayList<>();
        expectedContracts.add(new Contract(1, "Jessica Parker"));
        expectedContracts.add(new Contract(2, "Jacob Wisely"));
        when(contractRepo.findAll()).thenReturn(expectedContracts);

        // Act
        List<Contract> actualContracts = contractService.getAllContracts();

        // Assert
        assertEquals(expectedContracts, actualContracts);
        verify(contractRepo, times(1)).findAll();
    }

    @Test
    void getContractById_ExistingId_ShouldReturnContractOptional() {
        // Arrange
        int contractId = 1;
        Contract expectedContract = new Contract(contractId, "Paul Johnson");
        when(contractRepo.findById(contractId)).thenReturn(Optional.of(expectedContract));

        // Act
        Optional<Contract> actualContractOptional = contractService.getContractById(contractId);

        // Assert
        assertTrue(actualContractOptional.isPresent());
        assertEquals(expectedContract, actualContractOptional.get());
        verify(contractRepo, times(1)).findById(contractId);
    }

    @Test
    void getContractById_NonExistingId_ShouldReturnEmptyOptional() {
        // Arrange
        int nonExistingContractId = 999;
        when(contractRepo.findById(nonExistingContractId)).thenReturn(Optional.empty());

        // Act
        Optional<Contract> actualContractOptional = contractService.getContractById(nonExistingContractId);

        // Assert
        assertFalse(actualContractOptional.isPresent());
        verify(contractRepo, times(1)).findById(nonExistingContractId);
    }

    @Test
    void addContract_ShouldReturnSavedContract() {
        // Arrange
        Contract contractToSave = new Contract(1, "Mark Marvin");
        when(contractRepo.save(contractToSave)).thenReturn(contractToSave);

        // Act
        Contract savedContract = contractService.addContract(contractToSave);

        // Assert
        assertEquals(contractToSave, savedContract);
        verify(contractRepo, times(1)).save(contractToSave);
    }

    @Test
    void updateContract_ExistingContractId_ShouldUpdateAndSaveContract() {
        // Arrange
        int existingContractId = 1;
        Contract existingContract = new Contract(existingContractId, "Kim");
        Contract updatedContract = new Contract(existingContractId, "Kim Bryant");
        when(contractRepo.findById(existingContractId)).thenReturn(Optional.of(existingContract));
        when(contractRepo.save(updatedContract)).thenReturn(updatedContract);

        // Act 
        contractService.updateContract(existingContractId, updatedContract);

        // Assert
        verify(contractRepo, times(1)).findById(existingContractId);
        verify(contractRepo, times(1)).save(updatedContract);
        assertEquals(existingContractId, updatedContract.getContractId());
    }

    @Test
    void updateContract_NonExistingContract_ShouldNotUpdateContract() {
        // Arrange
        int nonExistingContractId = 1;
        Contract updatedContract = new Contract(nonExistingContractId, "Updated Contract 1");
        when(contractRepo.findById(nonExistingContractId)).thenReturn(Optional.empty());

        // Act
        contractService.updateContract(nonExistingContractId, updatedContract);

        // Assert
        verify(contractRepo, times(1)).findById(nonExistingContractId);
        verify(contractRepo, never()).save(updatedContract);
    }
}
