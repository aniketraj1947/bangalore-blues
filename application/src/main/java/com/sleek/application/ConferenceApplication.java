package com.sleek.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import jakarta.servlet.ServletException;
;
@SpringBootApplication
public class ConferenceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}

}
