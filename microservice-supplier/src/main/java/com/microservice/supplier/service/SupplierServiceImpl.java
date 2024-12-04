package com.microservice.supplier.service;

import com.microservice.supplier.dto.SupplierDTO;
import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.CategoryEntity;
import com.microservice.supplier.model.SupplierEntity;
import com.microservice.supplier.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements ISupplierService{
    @Autowired
    private ISupplierRepository supplierRepository;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public SupplierEntity create(SupplierDTO supplierDTO) throws ResourceNotFoundException {
        if (!categoryService.doCategoriesExist(supplierDTO.getCategories())){
            throw new ResourceNotFoundException("Failed to save supplier, some categories were not found");
        }
        SupplierEntity supplier = this.mapDtoToEntity(supplierDTO);
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!supplierRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Failed to delete, supplier not found");
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierEntity update(Long id,SupplierDTO updatedSupplierDTO) throws ResourceNotFoundException {
        if(!supplierRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Failed to update, supplier not found");
        }
        if (!categoryService.doCategoriesExist(updatedSupplierDTO.getCategories())) {
            throw new ResourceNotFoundException("Failed to update supplier, some categories were not found");
        }
        SupplierEntity updatedSupplier = this.mapDtoToEntity(updatedSupplierDTO);
        updatedSupplier.setSupplierId(id);
        return supplierRepository.save(updatedSupplier);
    }

    @Override
    public SupplierEntity findById(Long id) throws ResourceNotFoundException {
        //return supplierRepository.findById(id).orElseThrow(new SupplierNotFoundException("Supplier not found"));
        Optional<SupplierEntity> supplier = supplierRepository.findById(id);
        if (!supplier.isPresent()){
            throw new ResourceNotFoundException("Supplier not found");
        }
        return supplier.get();
    }

    @Override
    public List<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public List<SupplierEntity> findAllByCategory(Long categoryId) {
        return supplierRepository.findDistinctByCategoriesCategoryId(categoryId);
    }

    @Override
    public SupplierEntity mapDtoToEntity(SupplierDTO supplierDTO) {
        SupplierEntity supplier = new SupplierEntity();
        supplier.setName(supplierDTO.getName());
        supplier.setSurname(supplierDTO.getSurname());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setPhone(supplierDTO.getPhone());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setProductsDescription(supplierDTO.getProductsDescription());
        supplier.setUsername(supplierDTO.getUsername());
        supplier.setRating(supplierDTO.getRating());

        List<CategoryEntity> categories = categoryService.findCategoriesById(supplierDTO.getCategories());
        supplier.setCategories(categories);
        return supplier;
    }
}
