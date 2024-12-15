package com.microservice.order.service;

import com.microservice.order.dto.InvoiceResponse;
import com.microservice.order.dto.UpdateInvoiceStatusRequest;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.model.InvoiceEntity;
import com.microservice.order.model.InvoiceStatus;
import com.microservice.order.model.OrderEntity;

import java.util.List;

public interface IInvoiceService {
    void createForOrder(OrderEntity orderEntity);
    List<InvoiceEntity> findAll();
    InvoiceResponse findById(Long id) throws ResourceNotFoundException;
    boolean isValidStatus(String status);
    InvoiceEntity findByOrder(Long orderId) throws ResourceNotFoundException;
    void markAsCancelled(Long id)throws InvalidArgumentException, ResourceNotFoundException;
    void markAsPaid(Long id) throws InvalidArgumentException, ResourceNotFoundException;
    void markAsCancelled(OrderEntity orderEntity)throws InvalidArgumentException, ResourceNotFoundException;
    void markAsPaid(OrderEntity orderEntity) throws InvalidArgumentException, ResourceNotFoundException;
}
