package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.dao.entity"})
@ComponentScan(basePackages = { "com.example.*" })
@EnableJpaRepositories(basePackages = {"com.example.dao.repository"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
