package com.microservice.supplier.service;

import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.CategoryEntity;
import com.microservice.supplier.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public void save(CategoryEntity category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException{
        if (!categoryRepository.findById(id).isPresent()) throw new ResourceNotFoundException("Category not found");
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryEntity findById(Long id) throws ResourceNotFoundException{
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (!category.isPresent()) throw new ResourceNotFoundException("Category not found");
        return category.get();
    }

    @Override
    public void update(Long id, CategoryEntity updatedCategory) throws ResourceNotFoundException{
        if (!categoryRepository.findById(id).isPresent()) throw new ResourceNotFoundException("Failed to update, category not found");
        updatedCategory.setCategoryId(id);
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
