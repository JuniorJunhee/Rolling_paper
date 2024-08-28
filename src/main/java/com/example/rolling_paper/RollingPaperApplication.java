package com.example.rolling_paper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RollingPaperApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollingPaperApplication.class, args);
	}

}
