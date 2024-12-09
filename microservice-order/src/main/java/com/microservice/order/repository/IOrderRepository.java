package com.microservice.order.repository;

import com.microservice.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllBySupplierId(Long supplierId);
    List<OrderEntity> findAllByCustomerId(Long customerId);
    List<OrderEntity> findAllBySupplierIdAndCustomerId(Long supplierId, Long customerId);
}
