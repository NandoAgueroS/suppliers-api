package com.microservice.order.client;

import com.microservice.order.dto.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-supplier", url = "localhost:8090/supplier")
public interface SupplierClient {
    @GetMapping("/exists/{id}")
    boolean existsById(@PathVariable Long id);
    @GetMapping("/find/{id}")
    SupplierDTO findById(@PathVariable Long id);
}
