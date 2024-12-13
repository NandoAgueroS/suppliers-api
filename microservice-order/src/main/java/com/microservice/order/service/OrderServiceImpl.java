package com.microservice.order.service;

import com.microservice.order.client.CustomerClient;
import com.microservice.order.client.SupplierClient;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.http.response.CustomerDTO;
import com.microservice.order.http.response.OrderResponse;
import com.microservice.order.http.response.SupplierDTO;
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

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private SupplierClient supplierClient;

    @Override
    public void create(OrderEntity order) throws InvalidArgumentException {
        boolean customerExists = customerClient.existsById(order.getCustomerId());
        boolean supplierExists = supplierClient.existsById(order.getSupplierId());
        if (!supplierExists && !customerExists) throw new InvalidArgumentException("Failed to save: neither the specified supplier nor customer exists");
        else if (!supplierExists) throw new InvalidArgumentException("Failed to save: the specified supplier does not exist");
        else if (!customerExists) throw new InvalidArgumentException("Failed to save: the specified customer does not exist");
        else orderRepository.save(order);
    }

    @Override
    public void update(Long id, OrderEntity updatedOrder) throws ResourceNotFoundException {
        if (!orderRepository.existsById(id)) throw new ResourceNotFoundException("Failed to update: order not found");
        updatedOrder.setOrderId(id);
        orderRepository.save(updatedOrder);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (!orderRepository.existsById(id)) throw new ResourceNotFoundException("Failed to delete: order not found");
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponse findById(Long id) throws ResourceNotFoundException {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) throw new ResourceNotFoundException("Order not found.");
        SupplierDTO supplierDTO = supplierClient.findById(optionalOrder.get().getSupplierId());
        CustomerDTO customerDTO = customerClient.findById(optionalOrder.get().getCustomerId());
        return OrderResponse.builder()
                .orderId(optionalOrder.get().getOrderId())
                .orderDate(optionalOrder.get().getOrderDate())
                .total(optionalOrder.get().getTotal())
                .description(optionalOrder.get().getDescription())
                .priority(optionalOrder.get().getPriority())
                .status(optionalOrder.get().getStatus())
                .dueDate(optionalOrder.get().getDueDate())
                .customer(customerDTO)
                .supplier(supplierDTO)
                .build();
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
