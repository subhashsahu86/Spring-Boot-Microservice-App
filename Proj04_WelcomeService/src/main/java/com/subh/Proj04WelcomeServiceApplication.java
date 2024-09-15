package com.subh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class Proj04WelcomeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj04WelcomeServiceApplication.class, args);
	}

}
