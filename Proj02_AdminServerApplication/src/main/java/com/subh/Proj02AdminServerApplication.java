package com.subh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class Proj02AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj02AdminServerApplication.class, args);
	}

}
