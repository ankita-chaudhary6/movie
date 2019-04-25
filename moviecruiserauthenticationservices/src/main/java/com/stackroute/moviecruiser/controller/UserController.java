package com.stackroute.moviecruiser.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiser.model.User;
import com.stackroute.moviecruiser.service.SecurityTokenGenerator;
import com.stackroute.moviecruiser.service.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/api/userservice")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("message :" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		try {
			String userId = user.getUserId();
			String password = user.getPassword();
			if (userId == null || password == null) {
				throw new Exception("username or passwrd can not be empty");
			}
			User fetchedUser = userService.findByUserIdAndPassword(userId, password);

			Map<String, String> map = tokenGenerator.generateToken(fetchedUser);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("message" + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}

