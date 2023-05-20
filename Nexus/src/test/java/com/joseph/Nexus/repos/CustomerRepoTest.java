package com.joseph.Nexus.repos;

import com.joseph.Nexus.models.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
class CustomerRepoTest {

    @Mock
    private CustomerRepo customerRepo;

    @Test
    void findAllByCustomerId_ShouldReturnListOfCustomers() {
        // Arrange
        int customerId = 1;
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1, "Customer 1"));
        expectedCustomers.add(new Customer(2, "Customer 2"));
        when(customerRepo.findAllByCustomerId(customerId)).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerRepo.findAllByCustomerId(customerId);

        // Assert
        assertEquals(expectedCustomers, actualCustomers);
    }
}
