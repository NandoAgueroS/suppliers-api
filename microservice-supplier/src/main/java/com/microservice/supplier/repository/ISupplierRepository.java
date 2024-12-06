package com.microservice.supplier.repository;

import com.microservice.supplier.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
    public List<SupplierEntity> findDistinctByCategoriesCategoryId(Long categoryId);
    Optional<SupplierEntity> findByUsername(String username);
}
