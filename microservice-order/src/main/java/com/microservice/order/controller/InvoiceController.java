package com.microservice.order.controller;

import com.microservice.order.dto.InvoiceResponse;
import com.microservice.order.dto.UpdateInvoiceStatusRequest;
import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.model.InvoiceEntity;
import com.microservice.order.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping
    public List<InvoiceEntity> findAll(){
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @PatchMapping("/{id}/mark-cancelled")
    public void markAsCancelled(@PathVariable Long id) throws InvalidArgumentException, ResourceNotFoundException {
        invoiceService.markAsCancelled(id);
    }

    @PatchMapping("/{id}/mark-paid")
    public void markAsPaid(@PathVariable Long id) throws InvalidArgumentException, ResourceNotFoundException {
        invoiceService.markAsPaid(id);
    }
}
