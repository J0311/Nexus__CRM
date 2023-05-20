package com.joseph.Nexus.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.repos.ContractRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ContractService.class})
@ExtendWith(SpringExtension.class)
class ContractServiceTest {
    @MockBean
    private ContractRepo contractRepo;

    @Autowired
    private ContractService contractService;

    /**
     * Method under test: {@link ContractService#getAllContracts()}
     */
    @Test
    void testGetAllContracts() {
        ArrayList<Contract> contractList = new ArrayList<>();
        when(contractRepo.findAll()).thenReturn(contractList);
        List<Contract> actualAllContracts = contractService.getAllContracts();
        assertSame(contractList, actualAllContracts);
        assertTrue(actualAllContracts.isEmpty());
        verify(contractRepo).findAll();
    }

    /**
     * Method under test: {@link ContractService#getContractById(int)}
     */
    @Test
    void testGetContractById() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);
        Optional<Contract> ofResult = Optional.of(contract);
        when(contractRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Contract> actualContractById = contractService.getContractById(1);
        assertSame(ofResult, actualContractById);
        assertTrue(actualContractById.isPresent());
        verify(contractRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link ContractService#addContract(Contract)}
     */
    @Test
    void testAddContract() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);
        when(contractRepo.save((Contract) any())).thenReturn(contract);

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);
        assertSame(contract, contractService.addContract(contract1));
        verify(contractRepo).save((Contract) any());
    }

    /**
     * Method under test: {@link ContractService#updateContract(int, Contract)}
     */
    @Test
    void testUpdateContract() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);
        Optional<Contract> ofResult = Optional.of(contract);

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);
        when(contractRepo.save((Contract) any())).thenReturn(contract1);
        when(contractRepo.findById((Integer) any())).thenReturn(ofResult);

        Contract contract2 = new Contract();
        contract2.setClient_name("Dr Jane Doe");
        contract2.setContractId(123);
        contract2.setPending(true);
        contractService.updateContract(123, contract2);
        verify(contractRepo).save((Contract) any());
        verify(contractRepo).findById((Integer) any());
        assertEquals(123, contract2.getContractId());
    }

    /**
     * Method under test: {@link ContractService#updateContract(int, Contract)}
     */
    @Test
    void testUpdateContract2() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);
        when(contractRepo.save((Contract) any())).thenReturn(contract);
        when(contractRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);
        contractService.updateContract(123, contract1);
        verify(contractRepo).findById((Integer) any());
        assertEquals("Dr Jane Doe", contract1.getClient_name());
        assertTrue(contract1.isPending());
        assertEquals(123, contract1.getContractId());
    }

    /**
     * Method under test: {@link ContractService#deleteContract(int)}
     */
    @Test
    void testDeleteContract() {
        doNothing().when(contractRepo).deleteById((Integer) any());
        contractService.deleteContract(1);
        verify(contractRepo).deleteById((Integer) any());
    }
}

