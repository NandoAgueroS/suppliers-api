package com.microservice.supplier.service;

import com.microservice.supplier.model.CategoryEntity;
import com.microservice.supplier.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public void save(CategoryEntity category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void modify(CategoryEntity modifiedCategory) {
        categoryRepository.save(modifiedCategory);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}
