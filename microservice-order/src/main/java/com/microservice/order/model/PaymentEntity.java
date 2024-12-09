package com.microservice.order.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @CreationTimestamp
    private LocalDateTime date;
    private double amount;
    @Column(name = "payment_method")
    private String paymentMethod;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
