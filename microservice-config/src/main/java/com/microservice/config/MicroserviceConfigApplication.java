package com.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

@EnableConfigServer
@SpringBootApplication
public class MicroserviceConfigApplication {
	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigApplication.class, args);
	}

}
