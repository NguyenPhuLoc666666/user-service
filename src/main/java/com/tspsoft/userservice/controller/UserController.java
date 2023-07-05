package com.tspsoft.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tspsoft.userservice.dto.UserRequest;
import com.tspsoft.userservice.dto.UserResponse;
import com.tspsoft.userservice.entity.UserEntity;
import com.tspsoft.userservice.services.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0/user")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/secret-info")
	private ResponseEntity secretInfo() {
		return ResponseEntity.ok("This secret is no secret!");
	}
	
	@GetMapping("/logout")
	private ResponseEntity logout() {
		userService.logout();
		return ResponseEntity.ok("This logout is logout!");
	}

	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody UserEntity user) {
		log.info("401 unauthoried registerUser ------------------------------------------------------");
		userService.createUser(user);
		log.info("User is registerUser");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/saveUser")
	public ResponseEntity<Void> createUser(@RequestBody UserEntity user) {
		userService.saveUser(user);
		log.info("User is created!");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<UserResponse> ListUsers = userService.getAllUsers();
		return new ResponseEntity<>(ListUsers, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<Void> updateUser(@RequestParam(value = "id") Long userId,
			@Valid @RequestBody UserRequest userDetails) {
		userService.updateUser(userId, userDetails);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam(value = "id") Long userId) {
		userService.deleteById(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
