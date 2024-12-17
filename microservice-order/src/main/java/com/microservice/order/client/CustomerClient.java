package com.microservice.order.client;

import com.microservice.order.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-customer", url = "localhost:8080/api/customers")
public interface CustomerClient {

    @GetMapping("/{id}/exists")
    Boolean existsById(@PathVariable Long id);

    @GetMapping("/{id}")
    CustomerDTO findById(@PathVariable Long id);
}
