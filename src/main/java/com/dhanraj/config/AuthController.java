package com.dhanraj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanraj.model.JwtRequest;
import com.dhanraj.model.JwtResponse;
import com.dhanraj.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelper helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
		try {
			// Authenticate user
			this.doAuthenticate(request.getEmail(), request.getPassword());

			// Load user details
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

			// Generate JWT token
			String token = helper.generateToken(userDetails);

			// Create response
			JwtResponse response = JwtResponse.builder()
			        .jwtToken(token)
			        .user(userDetails.getUsername())
			        .build();


			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			logger.error("Invalid username or password");
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	private void doAuthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		manager.authenticate(authenticationToken);
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public String  exceptionHandler() {
		
		return "Credentials Invalid";
	}
}
