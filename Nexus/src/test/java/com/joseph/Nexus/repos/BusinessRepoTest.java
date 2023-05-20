package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Business;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class BusinessRepoTest {

    @Mock
    private BusinessRepo businessRepo;

    @Test
    void findAllByBusinessId_ShouldReturnListOfBusinesses() {
        // Arrange
        int businessId = 1;
        List<Business> expectedBusinesses = new ArrayList<>();
        expectedBusinesses.add(new Business(1, "Business 1"));
        expectedBusinesses.add(new Business(2, "Business 2"));
        when(businessRepo.findAllByBusinessId(businessId)).thenReturn(expectedBusinesses);

        // Act
        List<Business> actualBusinesses = businessRepo.findAllByBusinessId(businessId);

        // Assert
        assertEquals(expectedBusinesses, actualBusinesses);
    }
}


