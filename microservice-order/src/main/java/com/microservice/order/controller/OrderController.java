package com.microservice.order.controller;

import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.model.OrderEntity;
import com.microservice.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/find-all")
    public List<OrderEntity> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderEntity> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.findById(id));
    }

    @GetMapping("/find-all-by-supplier/{supplierId}")
    public List<OrderEntity> findAllBySupplierId(@PathVariable Long supplierId) {
        return orderService.findAllBySupplierId(supplierId);
    }

    @GetMapping("/find-all-by-customer/{customerId}")
    public List<OrderEntity> findAllByCustomerId(@PathVariable Long customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @GetMapping("/find-all-by-supplier-and-customer")
    public List<OrderEntity> findAllBySupplierAndCustomerId(
            @RequestParam(name = "supplier") Long supplierId,
            @RequestParam(name = "customer") Long customerId) {
        return orderService.findAllBySupplierAndCustomerIds(supplierId, customerId);
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity create(@RequestBody OrderEntity order){
        orderService.create(order);
        return order;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable Long id, @RequestBody OrderEntity updatedOrder) throws ResourceNotFoundException{
        orderService.update(id, updatedOrder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedOrder);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws ResourceNotFoundException{
        orderService.delete(id);
    }
}
