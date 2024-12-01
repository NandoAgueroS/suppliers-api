package com.microservice.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSupplierApplication.class, args);
	}

}
