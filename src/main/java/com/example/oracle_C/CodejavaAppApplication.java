package com.example.oracle_C;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//Not run security login

/* (exclude = SecurityAutoConfiguration.class) */
@SpringBootApplication
public class CodejavaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodejavaAppApplication.class, args);
	}

}
