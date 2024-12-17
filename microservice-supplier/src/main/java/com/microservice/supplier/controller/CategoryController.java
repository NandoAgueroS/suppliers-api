package com.microservice.supplier.controller;

import com.microservice.supplier.error.ResourceNotFoundException;
import com.microservice.supplier.model.CategoryEntity;
import com.microservice.supplier.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CategoryEntity> categories = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                        .body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws ResourceNotFoundException {
        CategoryEntity category = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryEntity save(@RequestBody CategoryEntity category){
        categoryService.save(category);
        return category;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryEntity updatedCategory) throws ResourceNotFoundException{
        categoryService.update(id, updatedCategory);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCategory);
    }
}
