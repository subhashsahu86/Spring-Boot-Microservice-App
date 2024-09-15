package com.subh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.client.WelcomeFeignClient;

@RestController
public class GreetController {
	
	@Autowired
	private WelcomeFeignClient welcomeClient;
    
	@GetMapping("/greet")
	public  String getGreetMsg() {
		String greet = "Good Morning !!";
		String welcome = welcomeClient.getWelcomeMsg();
		return welcome +" "+greet ;
	}
}
