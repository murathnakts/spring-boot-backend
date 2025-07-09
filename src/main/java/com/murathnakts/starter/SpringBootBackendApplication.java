package com.murathnakts.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.murathnakts"})
@SpringBootApplication
public class SpringBootBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}

}
