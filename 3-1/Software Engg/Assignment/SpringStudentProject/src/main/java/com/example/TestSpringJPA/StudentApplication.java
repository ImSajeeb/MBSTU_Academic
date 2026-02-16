package com.example.TestSpringJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		// This starts the Spring Boot application and connects to your 'test' database
		SpringApplication.run(StudentApplication.class, args);
	}

}