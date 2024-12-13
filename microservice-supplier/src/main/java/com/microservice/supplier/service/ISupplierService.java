package com.microservice.supplier.service;

import com.microservice.supplier.dto.SupplierDTO;
import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.SupplierEntity;

import java.util.List;

public interface ISupplierService {

    SupplierEntity create(SupplierDTO supplierDTO) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;
    SupplierEntity update(Long id, SupplierDTO updatedSupplierDTO) throws ResourceNotFoundException;
    SupplierEntity findById(Long id) throws ResourceNotFoundException;
    List<SupplierEntity> findAll();
    List<SupplierEntity> findAllByCategory(Long categoryId);
    SupplierEntity mapDtoToEntity(SupplierDTO supplierDTO);
    SupplierEntity findByUsername(String username)throws ResourceNotFoundException;
    Boolean existsById(Long id);
}
