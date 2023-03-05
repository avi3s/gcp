package com.test.gcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "gcp",scheme = "bearer",type = SecuritySchemeType.HTTP,in = SecuritySchemeIn.HEADER)
public class GcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpApplication.class, args);
	}

}
