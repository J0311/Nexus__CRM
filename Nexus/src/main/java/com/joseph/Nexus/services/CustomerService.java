package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Customer;
import com.joseph.Nexus.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepo customerRepo;


    @Autowired
    public void setCustomerRepo (CustomerRepo CustomerRepo) {
        this.customerRepo = customerRepo;
    }

        public List<Customer> getAllCustomers () {
            return customerRepo.findAll();
        }

        public Optional<Customer> getCustomerById ( int customerId){
            return customerRepo.findById(customerId);
        }

        public Customer addCustomer (Customer customer){
            return customerRepo.save(customer);
        }

        public void updateCustomer ( int customerId, Customer customer){
            Optional<Customer> existingCustomer = customerRepo.findById(customerId);
            if (existingCustomer.isPresent()) {
                customer.setCustomerId(customerId);
                customerRepo.save(customer);
            }
        }

        public void deleteCustomer ( int customerId){
            Optional<Customer> customer = customerRepo.findById(customerId);
            if (customer.isPresent()) {
                customerRepo.deleteById(customerId);
            }
        }
    }
