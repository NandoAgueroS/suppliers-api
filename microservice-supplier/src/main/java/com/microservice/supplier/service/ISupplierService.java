package com.microservice.supplier.service;

import com.microservice.supplier.model.SupplierEntity;

import java.util.List;

public interface ISupplierService {

    public void create(SupplierEntity supplier);
    public void deleteById(Long id);
    public SupplierEntity modify(SupplierEntity modifiedSupplier);
    public SupplierEntity findById(Long id);
    public List<SupplierEntity> findAll();
    public List<SupplierEntity> findAllByCategory(Long categoryId);
}
