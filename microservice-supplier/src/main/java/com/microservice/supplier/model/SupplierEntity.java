package com.microservice.supplier.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String email;
    @Column(name = "products_description")
    private String productsDescription;
    private String phone;
    private Double rating;
    private boolean status;
    private String username;
    @ManyToMany
    @JoinTable(
            name = "suppliers_categories",
            joinColumns = @JoinColumn(name = "supplier_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private List<CategoryEntity> categories;
}
