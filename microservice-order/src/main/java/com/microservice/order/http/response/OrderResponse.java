package com.microservice.order.http.response;

import com.microservice.order.model.OrderStatus;
import com.microservice.order.model.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private double total;
    private OrderStatus status;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private SupplierDTO supplier;
    private CustomerDTO customer;
}
