package com.minipreplexity.minipreplexity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.minipreplexity.minipreplexity.model")
@EnableJpaRepositories(basePackages = "com.minipreplexity.minipreplexity.Repo")
public class MinipreplexityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinipreplexityApplication.class, args);
	}
}
