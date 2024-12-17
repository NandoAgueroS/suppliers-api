package com.microservice.order.controller;

import com.microservice.order.dto.UpdateOrderStatusRequest;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.dto.OrderResponse;
import com.microservice.order.model.OrderEntity;
import com.microservice.order.model.OrderStatus;
import com.microservice.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.findById(id));
    }

    @GetMapping
    public List<OrderEntity> findOrders(
            @RequestParam(name = "supplier", required = false) Long supplierId,
            @RequestParam(name = "customer", required = false) Long customerId) {
        if (supplierId != null && customerId != null){
            return orderService.findAllBySupplierAndCustomerIds(supplierId, customerId);
        }else if (supplierId != null){
            return orderService.findAllBySupplierId(supplierId);
        }else if (customerId != null){
            return orderService.findAllByCustomerId(customerId);
        }
        return orderService.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity create(@RequestBody OrderEntity order) throws InvalidArgumentException {
        orderService.create(order);
        return order;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable Long id, @RequestBody OrderEntity updatedOrder) throws ResourceNotFoundException{
        orderService.update(id, updatedOrder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException{
        orderService.delete(id);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest status) throws ResourceNotFoundException, InvalidArgumentException{
        orderService.updateStatus(id ,status);
    }
}
