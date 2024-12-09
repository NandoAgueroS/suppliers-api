package com.microservice.order.service;

import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.model.OrderEntity;
import com.microservice.order.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public void create(OrderEntity order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Long id, OrderEntity updatedOrder) throws ResourceNotFoundException {
        if (!orderRepository.existsById(id)) throw new ResourceNotFoundException("Failed to update, order not found");
        updatedOrder.setOrderId(id);
        orderRepository.save(updatedOrder);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (!orderRepository.existsById(id)) throw new ResourceNotFoundException("Failed to delete, order not found");
        orderRepository.deleteById(id);
    }

    @Override
    public OrderEntity findById(Long id) throws ResourceNotFoundException {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) throw new ResourceNotFoundException("Order not found.");
        return optionalOrder.get();
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> findAllBySupplierId(Long supplierId) {
        return orderRepository.findAllBySupplierId(supplierId);
    }

    @Override
    public List<OrderEntity> findAllByCustomerId(Long customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Override
    public List<OrderEntity> findAllBySupplierAndCustomerIds(Long supplierId, Long customerId) {
        return orderRepository.findAllBySupplierIdAndCustomerId(supplierId, customerId);
    }
}
