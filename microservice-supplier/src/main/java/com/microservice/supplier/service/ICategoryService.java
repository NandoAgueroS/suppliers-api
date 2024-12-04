package com.microservice.supplier.service;

import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public void save(CategoryEntity category);
    public void delete(Long id) throws ResourceNotFoundException;
    public CategoryEntity findById(Long id) throws ResourceNotFoundException ;
    public void update(Long id, CategoryEntity updatedCategory) throws ResourceNotFoundException;
    public List<CategoryEntity> findAll();
    public List<CategoryEntity> findCategoriesById(List<Long> categoryIds);
    boolean doCategoriesExist(List<Long> categoryIds);
}
