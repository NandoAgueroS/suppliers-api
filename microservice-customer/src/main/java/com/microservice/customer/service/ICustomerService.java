package com.microservice.customer.service;

import com.microservice.customer.error.ResourceNotFoundException;
import com.microservice.customer.model.CustomerEntity;

import java.util.List;

public interface ICustomerService {
    CustomerEntity create(CustomerEntity customer);
    void deleteById(Long id) throws ResourceNotFoundException;
    CustomerEntity update(Long id, CustomerEntity updatedCustomer) throws ResourceNotFoundException;
    CustomerEntity findById(Long id) throws ResourceNotFoundException;
    List<CustomerEntity> findAll();
    CustomerEntity findByUsername(String username);
}
