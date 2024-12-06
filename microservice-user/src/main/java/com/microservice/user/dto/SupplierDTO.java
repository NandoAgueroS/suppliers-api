package com.microservice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String name;
    private String surname;
    private String address;
    private String email;
    private String productsDescription;
    private String phone;
    private Double rating;
    private boolean status;
    private String username;
    private List<CategoryDTO> categories;
}
