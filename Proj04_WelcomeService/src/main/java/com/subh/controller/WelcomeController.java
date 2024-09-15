package com.subh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		System.out.println("WelcomeController.getWelcomeMsg()");
		
		return "Welcome Rest API";
	}
}
