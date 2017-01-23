package com.perficient.cloud.coe;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.perficient.cloud.domain")
@EnableJpaRepositories("com.perficient.cloud.repository")
public class CloudCoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCoeApplication.class, args);
	}
	
}
