package com.dhanraj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.model.User;
import com.dhanraj.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@Autowired
	private UserService userService;

	
	// localhost:8080/home/user
	
	@GetMapping("/user")
	public List<User> getUser() {

		System.out.println("Getting users");
		
		return this.userService.getUser();
	}
	
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();	
	}
	
	
}
