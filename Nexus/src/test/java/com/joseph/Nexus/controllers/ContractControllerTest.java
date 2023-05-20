package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.services.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ContractControllerTest {

    @Mock
    private ContractService contractService;

    private ContractController contractController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        contractController = new ContractController();
        
    }

    @Test
    void getAllContracts_ReturnsListOfContracts() {
        // Arrange
        List<Contract> expectedContracts = new ArrayList<>();
        expectedContracts.add(new Contract(1, "Contract 1"));
        expectedContracts.add(new Contract(2, "Contract 2"));
        when(contractService.getAllContracts()).thenReturn(expectedContracts);

        // Act
        ResponseEntity<List<Contract>> response = contractController.getAllContracts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedContracts, response.getBody());
    }

    @Test
    void getContractById_ExistingContractId_ReturnsContract() {
        // Arrange
        int contractId = 1;
        Contract expectedContract = new Contract(1, "Contract 1");
        when(contractService.getContractById(contractId)).thenReturn(Optional.of(expectedContract));

        // Act
        ResponseEntity<Contract> response = contractController.getContractById(contractId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedContract, response.getBody());
    }

    @Test
    void getContractById_NonExistingContractId_ReturnsNotFound() {
        // Arrange
        int nonExistingContractId = 1;
        when(contractService.getContractById(nonExistingContractId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Contract> response = contractController.getContractById(nonExistingContractId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addContract_ValidContract_ReturnsCreated() {
        // Arrange
        Contract contract = new Contract(1, "New Contract");
        when(contractService.addContract(contract)).thenReturn(contract);

        // Act
        ResponseEntity<Contract> response = contractController.addContract(contract);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(contract, response.getBody());
    }

    @Test
    void updateContract_ExistingContractId_ReturnsOk() {
        // Arrange
        int contractId = 1;
        Contract updatedContract = new Contract(1, "Updated Contract");

        // Act
        ResponseEntity<Void> response = contractController.updateContract(contractId, updatedContract);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(contractService, times(1)).updateContract(contractId, updatedContract);
    }

    @Test
    void deleteContract_ExistingContractId_ReturnsNoContent() {
        // Arrange
        int contractId = 1;

        // Act
        ResponseEntity<Void> response = contractController.deleteContract(contractId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(contractService, times(1)).deleteContract(contractId);
    }
}
