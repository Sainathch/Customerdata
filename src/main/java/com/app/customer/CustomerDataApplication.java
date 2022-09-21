package com.app.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CustomerDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataApplication.class, args);
	}

}
