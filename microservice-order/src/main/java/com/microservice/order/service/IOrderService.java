package com.microservice.order.service;

import com.microservice.order.dto.UpdateOrderStatusRequest;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.dto.OrderResponse;
import com.microservice.order.model.OrderEntity;
import com.microservice.order.model.OrderStatus;

import java.util.List;

public interface IOrderService {
    void create(OrderEntity order) throws InvalidArgumentException;
    void update(Long id, OrderEntity updatedOrder) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
    OrderResponse findById(Long id) throws ResourceNotFoundException;
    List<OrderEntity> findAll();
    List<OrderEntity> findAllBySupplierId(Long supplierId);
    List<OrderEntity> findAllByCustomerId(Long customerId);
    List<OrderEntity> findAllBySupplierAndCustomerIds(Long supplierId, Long customerId);

    void updateStatus(Long id, UpdateOrderStatusRequest status) throws ResourceNotFoundException, InvalidArgumentException;
    boolean isValidStatus(String status);
}
