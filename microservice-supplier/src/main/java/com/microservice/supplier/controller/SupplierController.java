package com.microservice.supplier.controller;

import com.microservice.supplier.model.SupplierEntity;
import com.microservice.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/findByCategory/{categoryId}")
    public List<SupplierEntity> findByCategory(@PathVariable Long categoryId){
        return supplierService.findAllByCategory(categoryId);
    }
}
