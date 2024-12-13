package com.microservice.customer.service;

import com.microservice.customer.error.ResourceNotFoundException;
import com.microservice.customer.model.CustomerEntity;
import com.microservice.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public CustomerEntity create(CustomerEntity customer){
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!customerRepository.existsById(id)) throw new ResourceNotFoundException("Failed to delete, customer not found");
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerEntity update(Long id, CustomerEntity updatedCustomer) throws ResourceNotFoundException {
        if (!customerRepository.existsById(id)) throw new ResourceNotFoundException("Failed to update, customer does not exists");
        updatedCustomer.setCustomerId(id);
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public CustomerEntity findById(Long id) throws ResourceNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) throw new ResourceNotFoundException("Customer not found");
        return optionalCustomer.get();
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findByUsername(String username){
        //Optional<CustomerEntity> optionalCustomer = customerRepository.findByUsername(username);
        //if (!optionalCustomer.isPresent()) throw new ResourceNotFoundException("Customer not found");
        //return optionalCustomer.get();
        CustomerEntity customer = customerRepository.findByUsername(username).orElse(null);
        return customer;
    }

    @Override
    public Boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}
