package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusinessServiceTest {

    @Mock
    private BusinessRepo businessRepo;

    private BusinessService businessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        businessService = new BusinessService(businessRepo);

    }

    @Test
    void getAllBusinesses_ShouldReturnAllBusinesses() {
        // Arrange
        List<Business> expectedBusinesses = new ArrayList<>();
        expectedBusinesses.add(new Business(1, "Business 1"));
        expectedBusinesses.add(new Business(2, "Business 2"));
        when(businessRepo.findAll()).thenReturn(expectedBusinesses);

        // Act
        List<Business> actualBusinesses = businessService.getAllBusinesses();

        // Assert
        assertEquals(expectedBusinesses, actualBusinesses);
        verify(businessRepo, times(1)).findAll();
    }

    @Test
    void getBusinessById_ExistingId_ShouldReturnBusiness() {
        // Arrange
        int businessId = 1;
        Business expectedBusiness = new Business(businessId, "Business 1");
        when(businessRepo.findById(businessId)).thenReturn(Optional.of(expectedBusiness));

        // Act
        Optional<Business> actualBusiness = businessService.getBusinessById(businessId);

        // Assert
        assertTrue(actualBusiness.isPresent());
        assertEquals(expectedBusiness, actualBusiness.get());
        verify(businessRepo, times(1)).findById(businessId);
    }

    @Test
    void getBusinessById_NonExistingId_ShouldReturnEmptyOptional() {
        // Arrange
        int businessId = 1;
        when(businessRepo.findById(businessId)).thenReturn(Optional.empty());

        // Act
        Optional<Business> actualBusiness = businessService.getBusinessById(businessId);

        // Assert
        assertFalse(actualBusiness.isPresent());
        verify(businessRepo, times(1)).findById(businessId);
    }

    @Test
    void addBusiness_ShouldSaveAndReturnBusiness() {
        // Arrange
        Business business = new Business(1, "Business 1");
        when(businessRepo.save(business)).thenReturn(business);

        // Act
        Business savedBusiness = businessService.addBusiness(business);

        // Assert
        assertEquals(business, savedBusiness);
        verify(businessRepo, times(1)).save(business);
    }

    @Test
    void updateBusiness_ExistingBusiness_ShouldUpdateAndSaveBusiness() {
        // Arrange
        int businessId = 1;
        Business existingBusiness = new Business(businessId, "Business 1");
        Business updatedBusiness = new Business(businessId, "Updated Business 1");
        when(businessRepo.findById(businessId)).thenReturn(Optional.of(existingBusiness));
        when(businessRepo.save(updatedBusiness)).thenReturn(updatedBusiness);

        // Act
        businessService.updateBusiness(businessId, updatedBusiness);

        // Assert
        verify(businessRepo, times(1)).findById(businessId);
        verify(businessRepo, times(1)).save(updatedBusiness);
        assertEquals(businessId, updatedBusiness.getBusinessId());
    }

    @Test
    void updateBusiness_NonExistingBusiness_ShouldNotUpdateBusiness() {
        // Arrange
        int nonExistingBusinessId = 1;
        Business updatedBusiness = new Business(nonExistingBusinessId, "Updated Business 1");
        when(businessRepo.findById(nonExistingBusinessId)).thenReturn(Optional.empty());

        // Act
        businessService.updateBusiness(nonExistingBusinessId, updatedBusiness);

        // Assert
        verify(businessRepo, times(1)).findById(nonExistingBusinessId);
        verify(businessRepo, never()).save(updatedBusiness);
    }
}


