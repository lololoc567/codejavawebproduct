package com.example.oracle_encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "456";
		String encoderpass= encoder.encode(rawPassword);
		System.out.println(encoderpass);
	}
}
