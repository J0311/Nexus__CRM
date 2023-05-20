package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.services.BusinessService;
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

class BusinessControllerTest {

    @Mock
    private BusinessService businessService;

    private BusinessController businessController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        businessController = new BusinessController();
    }

    @Test
    void getAllBusinesses_ReturnsListOfBusinesses() {
        // Arrange
        List<Business> expectedBusinesses = new ArrayList<>();
        expectedBusinesses.add(new Business(1, "Business 1"));
        expectedBusinesses.add(new Business(2, "Business 2"));
        when(businessService.getAllBusinesses()).thenReturn(expectedBusinesses);

        // Act
        ResponseEntity<List<Business>> response = businessController.getAllBusinesses();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBusinesses, response.getBody());
    }

    @Test
    void getBusinessById_ExistingBusinessId_ReturnsBusiness() {
        // Arrange
        int businessId = 1;
        Business expectedBusiness = new Business(1, "Business 1");
        when(businessService.getBusinessById(businessId)).thenReturn(Optional.of(expectedBusiness));

        // Act
        ResponseEntity<Business> response = businessController.getBusinessById(businessId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBusiness, response.getBody());
    }

    @Test
    void getBusinessById_NonExistingBusinessId_ReturnsNotFound() {
        // Arrange
        int nonExistingBusinessId = 1;
        when(businessService.getBusinessById(nonExistingBusinessId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Business> response = businessController.getBusinessById(nonExistingBusinessId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addBusiness_ValidBusiness_ReturnsCreated() {
        // Arrange
        Business business = new Business(1, "New Business");
        when(businessService.addBusiness(business)).thenReturn(business);

        // Act
        ResponseEntity<Business> response = businessController.addBusiness(business);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(business, response.getBody());
    }

    @Test
    void updateBusiness_ExistingBusinessId_ReturnsOk() {
        // Arrange
        int businessId = 1;
        Business updatedBusiness = new Business(1, "Updated Business");

        // Act
        ResponseEntity<Void> response = businessController.updateBusiness(businessId, updatedBusiness);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(businessService, times(1)).updateBusiness(businessId, updatedBusiness);
    }

    @Test
    void deleteBusiness_ExistingBusinessId_ReturnsNoContent() {
        // Arrange
        int businessId = 1;

        // Act
        ResponseEntity<Void> response = businessController.deleteBusiness(businessId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(businessService, times(1)).deleteBusiness(businessId);
    }
}