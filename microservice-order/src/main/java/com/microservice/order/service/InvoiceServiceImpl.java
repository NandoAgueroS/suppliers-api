package com.microservice.order.service;

import com.microservice.order.client.CustomerClient;
import com.microservice.order.client.SupplierClient;
import com.microservice.order.dto.CustomerDTO;
import com.microservice.order.dto.InvoiceResponse;
import com.microservice.order.dto.SupplierDTO;
import com.microservice.order.dto.UpdateInvoiceStatusRequest;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.model.InvoiceEntity;
import com.microservice.order.model.InvoiceStatus;
import com.microservice.order.model.OrderEntity;
import com.microservice.order.model.OrderStatus;
import com.microservice.order.repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements IInvoiceService{
    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private SupplierClient supplierClient;
    @Autowired
    private CustomerClient customerClient;

    @Override
    public void createForOrder(OrderEntity orderEntity) {
        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .order(orderEntity)
                .status(InvoiceStatus.PENDING)
                .dueDate(orderEntity.getDueDate())
                .totalAmount(orderEntity.getTotal())
                .build();
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public List<InvoiceEntity> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public void markAsCancelled(Long id) throws InvalidArgumentException, ResourceNotFoundException {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Failed to update: Invoice not found"));
        if (invoiceEntity.getStatus().equals(InvoiceStatus.CANCELLED))
            throw new InvalidArgumentException("Failed to update: The invoice has already been cancelled");
        invoiceEntity.setStatus(InvoiceStatus.CANCELLED);
        invoiceEntity.setInvoiceId(id);
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public void markAsPaid(Long id) throws InvalidArgumentException, ResourceNotFoundException {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Failed to update: Invoice not found"));
        if (!invoiceEntity.getStatus().equals(InvoiceStatus.PENDING))
            throw new InvalidArgumentException("Failed to update: The invoice status is not pending");
        invoiceEntity.setStatus(InvoiceStatus.PAID);
        invoiceEntity.setInvoiceId(id);
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public void markAsCancelled(OrderEntity orderEntity) throws InvalidArgumentException, ResourceNotFoundException {
        InvoiceEntity invoiceEntity = invoiceRepository.findByOrderOrderId(orderEntity.getOrderId())
                .orElseThrow(()-> new ResourceNotFoundException("Failed to update: Invoice not found"));
        if (invoiceEntity.getStatus().equals(InvoiceStatus.CANCELLED))
            throw new InvalidArgumentException("Failed to update: The invoice has already been cancelled");
        else if(invoiceEntity.getStatus().equals(InvoiceStatus.PAID))
            throw new InvalidArgumentException("Failed to update: You cannot cancel a paid invoice");
        invoiceEntity.setStatus(InvoiceStatus.CANCELLED);
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public void markAsPaid(OrderEntity orderEntity) throws InvalidArgumentException, ResourceNotFoundException {
        InvoiceEntity invoiceEntity = invoiceRepository.findByOrderOrderId(orderEntity.getOrderId())
                .orElseThrow(()-> new ResourceNotFoundException("Failed to update: Invoice not found"));
        if (invoiceEntity.getStatus() != InvoiceStatus.PENDING)
            throw new InvalidArgumentException("Failed to update: The invoice status is not pending");
        invoiceEntity.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoiceEntity);
    }

    @Override
    public InvoiceResponse findById(Long id) throws ResourceNotFoundException {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Invoice not found"));
        SupplierDTO supplierDTO = supplierClient.findById(invoiceEntity.getOrder().getSupplierId());
        CustomerDTO customerDTO = customerClient.findById(invoiceEntity.getOrder().getCustomerId());
        return InvoiceResponse.builder()
                .status(invoiceEntity.getStatus())
                .issueDate(invoiceEntity.getIssueDate())
                .dueDate(invoiceEntity.getDueDate())
                .totalAmount(invoiceEntity.getTotalAmount())
                .order(invoiceEntity.getOrder())
                .supplier(supplierDTO)
                .customer(customerDTO)
                .build();
    }

    @Override
    public boolean isValidStatus(String status) {
        try{
            InvoiceStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public InvoiceEntity findByOrder(Long orderId) throws ResourceNotFoundException {
        return invoiceRepository.findByOrderOrderId(orderId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Invoice not found"));
    }
}
