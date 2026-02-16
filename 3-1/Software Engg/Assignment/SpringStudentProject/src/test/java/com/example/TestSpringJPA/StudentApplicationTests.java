package com.example.TestSpringJPA;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Updated the class name to reflect the Student theme
@SpringBootTest
class StudentApplicationTests {

	@Test
	void contextLoads() {
		// This test ensures the Spring application context loads successfully.
		// It will fail if your database (test) is not reachable or
		// if your application.properties has incorrect credentials.
	}

}