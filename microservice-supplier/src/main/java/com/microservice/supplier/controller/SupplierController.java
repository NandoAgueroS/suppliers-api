package com.microservice.supplier.controller;

import com.microservice.supplier.dto.SupplierDTO;
import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.SupplierEntity;
import com.microservice.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(supplierService.findById(id));
    }
    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(supplierService.findByUsername(username));
    }

    @GetMapping("/findByCategory/{categoryId}")
    public List<SupplierEntity> findByCategory(@PathVariable Long categoryId) {
        return supplierService.findAllByCategory(categoryId);
    }

    @GetMapping("/findAll")
    public List<SupplierEntity> findAll(){
        return supplierService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws ResourceNotFoundException{
        supplierService.deleteById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SupplierDTO supplierDTO) throws ResourceNotFoundException {
        SupplierEntity supplier = supplierService.create(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(supplier);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody SupplierDTO updatedSupplierDTO) throws ResourceNotFoundException {
        SupplierEntity updatedSupplier = supplierService.update(id, updatedSupplierDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedSupplier);
    }
    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable Long id){
        return supplierService.existsById(id);
    }
}
