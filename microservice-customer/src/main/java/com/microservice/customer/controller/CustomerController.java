package com.microservice.customer.controller;

import com.microservice.customer.error.ResourceNotFoundException;
import com.microservice.customer.model.CustomerEntity;
import com.microservice.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> findById(@PathVariable Long id) throws ResourceNotFoundException {
        CustomerEntity customer = customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerEntity> findByUsername(@PathVariable String username){
        CustomerEntity customer = customerService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customer);
    }

    @GetMapping
    public List<CustomerEntity> findAll(){
        return customerService.findAll();
    }

    @PostMapping
    public CustomerEntity create(@RequestBody CustomerEntity customer){
        customerService.create(customer);
        return customer;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(
            @PathVariable Long id,
            @RequestBody CustomerEntity updatedCustomer) throws ResourceNotFoundException{
        customerService.update(id, updatedCustomer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException{
        customerService.deleteById(id);
    }
    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id){
        return customerService.existsById(id);
    }
}
