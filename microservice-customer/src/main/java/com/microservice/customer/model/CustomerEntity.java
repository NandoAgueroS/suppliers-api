package com.microservice.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String username;
    private String name;
    private String surname;
    private String address;
    private String email;
    @Column(name = "company_name")
    private String companyName;
    private String phone;
    private boolean status;
    private Double rating;
}
