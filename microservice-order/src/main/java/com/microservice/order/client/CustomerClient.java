package com.microservice.order.client;

import com.microservice.order.http.response.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-customer", url = "localhost:8085/customers")
public interface CustomerClient {

    @GetMapping("/exists/{id}")
    Boolean existsById(@PathVariable Long id);

    @GetMapping("/find/{id}")
    CustomerDTO findById(@PathVariable Long id);
}
