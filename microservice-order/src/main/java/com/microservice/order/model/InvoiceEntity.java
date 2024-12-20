package com.microservice.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @CreationTimestamp
    private LocalDateTime issueDate;
    //@ManyToOne
    //@JoinColumn(name = "order_id")
    //@OneToOne(mappedBy = "invoice")
    @OneToOne
    private OrderEntity order;
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    private LocalDate dueDate;
}
