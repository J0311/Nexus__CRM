package com.joseph.Nexus.controllers;

import com.joseph.Nexus.models.Customer;
import com.joseph.Nexus.services.CustomerService;
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

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    void getAllCustomers_ReturnsListOfCustomers() {
        // Arrange
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1, "Customer 1"));
        expectedCustomers.add(new Customer(2, "Customer 2"));
        when(customerService.getAllCustomers()).thenReturn(expectedCustomers);

        // Act
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomers, response.getBody());
    }

    @Test
    void getCustomerById_ExistingCustomerId_ReturnsCustomer() {
        // Arrange
        int customerId = 1;
        Customer expectedCustomer = new Customer(1, "Customer 1");
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCustomer, response.getBody());
    }

    @Test
    void getCustomerById_NonExistingCustomerId_ReturnsNotFound() {
        // Arrange
        int nonExistingCustomerId = 1;
        when(customerService.getCustomerById(nonExistingCustomerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Customer> response = customerController.getCustomerById(nonExistingCustomerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addCustomer_ValidCustomer_ReturnsCreated() {
        // Arrange
        Customer customer = new Customer(1, "New Customer");
        when(customerService.addCustomer(customer)).thenReturn(customer);

        // Act
        ResponseEntity<Customer> response = customerController.addCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void updateCustomer_ExistingCustomerId_ReturnsOk() {
        // Arrange
        int customerId = 1;
        Customer updatedCustomer = new Customer(1, "Updated Customer");

        // Act
        ResponseEntity<Void> response = customerController.updateCustomer(customerId, updatedCustomer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).updateCustomer(customerId, updatedCustomer);
    }

    @Test
    void deleteCustomer_ExistingCustomerId_ReturnsNoContent() {
        // Arrange
        int customerId = 1;

        // Act
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
