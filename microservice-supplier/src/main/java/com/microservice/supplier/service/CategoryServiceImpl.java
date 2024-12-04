package com.microservice.supplier.service;

import com.microservice.supplier.model.CategoryEntity;
import com.microservice.supplier.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
    public void update(CategoryEntity updatedCategory) {
        categoryRepository.save(updatedCategory);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryEntity> findCategoriesById(List<Long> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }

    @Override
    public boolean doCategoriesExist(List<Long> categoryIds) {
        List<CategoryEntity> categories = this.findCategoriesById(categoryIds);
        return categories.size() == categoryIds.size();
    }
}
