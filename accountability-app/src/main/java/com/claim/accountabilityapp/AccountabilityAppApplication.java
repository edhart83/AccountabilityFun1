package com.claim.accountabilityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.claim")
@SpringBootApplication
public class AccountabilityAppApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AccountabilityAppApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AccountabilityAppApplication.class, args);
	}

}
