package com.microservice.order.http.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String username;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String productsDescription;
    private String phone;
}
