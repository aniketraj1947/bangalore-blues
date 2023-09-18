package com.sleek.application;

import com.sleek.model.Property;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.sleek.model")
public class BangaloreBluesApplication {

	public static void main(String[] args) {
		new Property();
		SpringApplication.run(BangaloreBluesApplication.class, args);
	}

}
