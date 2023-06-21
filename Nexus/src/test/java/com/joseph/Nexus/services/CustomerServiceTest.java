package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Customer;
import com.joseph.Nexus.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepo);
    }

    @Test
    void getAllCustomers_ShouldReturnAllCustomers() {
        // Arrange
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1, "Customer 1"));
        expectedCustomers.add(new Customer(2, "Customer 2"));
        when(customerRepo.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepo, times(1)).findAll();
    }

    @Test
    void getCustomerById_ExistingId_ShouldReturnCustomer() {
        // Arrange
        int customerId = 1;
        Customer expectedCustomer = new Customer(customerId, "Customer 1");
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(customerId);

        // Assert
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedCustomer, actualCustomer.get());
        verify(customerRepo, times(1)).findById(customerId);
    }

    @Test
    void getCustomerById_NonExistingId_ShouldReturnEmptyOptional() {
        // Arrange
        int customerId = 1;
        when(customerRepo.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(customerId);

        // Assert
        assertFalse(actualCustomer.isPresent());
        verify(customerRepo, times(1)).findById(customerId);
    }

    @Test
    void addCustomer_ShouldSaveAndReturnCustomer() {
        // Arrange
        Customer customer = new Customer(1, "Customer 1");
        when(customerRepo.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.addCustomer(customer);

        // Assert
        assertEquals(customer, savedCustomer);
        verify(customerRepo, times(1)).save(customer);
    }

    @Test
    void updateCustomer_ExistingCustomer_ShouldUpdateAndSaveCustomer() {
        // Arrange
        int customerId = 1;
        Customer existingCustomer = new Customer(customerId, "Customer 1");
        Customer updatedCustomer = new Customer(customerId, "Updated Customer 1");
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepo.save(updatedCustomer)).thenReturn(updatedCustomer);

        // Act
        customerService.updateCustomer(customerId, updatedCustomer);

        // Assert
        verify(customerRepo, times(1)).findById(customerId);
        verify(customerRepo, times(1)).save(updatedCustomer);
        assertEquals(customerId, updatedCustomer.getCustomerId());
    }

    @Test
    void updateCustomer_NonExistingCustomer_ShouldNotUpdateCustomer() {
        // Arrange
        int nonExistingCustomerId = 1;
        Customer updatedCustomer = new Customer(nonExistingCustomerId, "Updated Customer 1");
        when(customerRepo.findById(nonExistingCustomerId)).thenReturn(Optional.empty());

        // Act
        customerService.updateCustomer(nonExistingCustomerId, updatedCustomer);

        // Assert
        verify(customerRepo, times(1)).findById(nonExistingCustomerId);
        verify(customerRepo, never()).save(updatedCustomer);
    }
}