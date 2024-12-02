package com.microservice.supplier.service;

import com.microservice.supplier.model.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public void save(CategoryEntity category);
    public void delete(Long id);
    public CategoryEntity findById(Long id);
    public void modify(CategoryEntity modifiedCategory);
    public List<CategoryEntity> findAll();
}
