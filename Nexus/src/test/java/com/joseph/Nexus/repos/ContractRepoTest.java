package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Contract;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class ContractRepoTest {

    @Mock
    private ContractRepo contractRepo;

    @Test
    void findAllByContractId_ShouldReturnListOfContracts() {
        // Arrange
        int contractId = 1;
        List<Contract> expectedContracts = new ArrayList<>();
        expectedContracts.add(new Contract(1, "Contract 1"));
        expectedContracts.add(new Contract(2, "Contract 2"));
        when(contractRepo.findAllByContractId(contractId)).thenReturn(expectedContracts);

        // Act
        List<Contract> actualContracts = contractRepo.findAllByContractId(contractId);

        // Assert
        assertEquals(expectedContracts, actualContracts);
    }
}
