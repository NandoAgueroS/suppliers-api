package com.microservice.user.client;

import com.microservice.user.dto.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-supplier", url = "localhost:8090/supplier")
public interface SupplierClient {
    @GetMapping("/find-by-username/{username}")
    SupplierDTO findByUsername(@PathVariable String username);
}
