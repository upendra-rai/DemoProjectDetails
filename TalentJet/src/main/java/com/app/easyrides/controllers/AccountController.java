package com.app.easyrides.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.easyrides.dtos.ForgetPassword;
import com.app.easyrides.dtos.ResetPassword;
import com.app.easyrides.entities.User;
import com.app.easyrides.services.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerAccount(@RequestBody User user) {
		log.info(">> registerAccount({})", user);
		accountService.registerAccount(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/reset")
	public ResponseEntity<Void> resetPassword(@RequestBody ResetPassword resetPassword) {
		log.info(">> resetPassword({})", resetPassword);
		accountService.resetPassword(resetPassword);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<Void> forgetPassword(@RequestBody ForgetPassword forgetPassword) {
		log.info(">> forgetPassword({})", forgetPassword);
		accountService.forgetPassword(forgetPassword);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
