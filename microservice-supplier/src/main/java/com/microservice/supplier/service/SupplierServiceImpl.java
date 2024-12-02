package com.microservice.supplier.service;

import com.microservice.supplier.model.SupplierEntity;
import com.microservice.supplier.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService{
    @Autowired
    private ISupplierRepository supplierRepository;
    @Override
    public void create(SupplierEntity supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierEntity modify(SupplierEntity modifiedSupplier) {
        return supplierRepository.save(modifiedSupplier);
    }

    @Override
    public SupplierEntity findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public List<SupplierEntity> findAllByCategory(Long categoryId) {
        return supplierRepository.findDistinctByCategoriesId(categoryId);
    }
}
