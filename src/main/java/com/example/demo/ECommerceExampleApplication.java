package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan("com.example.demo.model")
@ComponentScan(basePackages={"com.example.demo.controller","com.example.demo.repository","com.example.demo.service","com.example.demo.dto","com.example.demo.config"})
@Configuration
@EnableJpaRepositories(basePackages={"com.example.demo.repository"})
public class ECommerceExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceExampleApplication.class, args);
	}

}
