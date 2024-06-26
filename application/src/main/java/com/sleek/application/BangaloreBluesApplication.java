package com.sleek.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
;
@SpringBootApplication
@EntityScan("com.sleek.model")
public class BangaloreBluesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BangaloreBluesApplication.class, args);
	}

}
