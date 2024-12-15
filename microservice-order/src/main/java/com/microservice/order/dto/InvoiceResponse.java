package com.microservice.order.dto;

import com.microservice.order.model.InvoiceStatus;
import com.microservice.order.model.OrderEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private LocalDateTime issueDate;
    private OrderEntity order;
    private double totalAmount;
    private InvoiceStatus status;
    private LocalDate dueDate;
    private SupplierDTO supplier;
    private CustomerDTO customer;
}
