package com.app.easyrides.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.easyrides.dtos.ForgetPassword;
import com.app.easyrides.dtos.ResetPassword;
import com.app.easyrides.entities.User;
import com.app.easyrides.exception.ErrorCode;
import com.app.easyrides.exception.ServiceException;
import com.app.easyrides.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void registerAccount(User user) {
		log.info(">> registerAccount({})", user);
		user.setRole("ROLE_USER");
		user.setAccountStatus(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void resetPassword(ResetPassword resetPassword) {
		log.info(">> resetPassword({})", resetPassword);
		User user = userRepository.findByToken(resetPassword.getToken())
				.orElseThrow(() -> new ServiceException(ErrorCode.TOKEN_NOT_INVALID, resetPassword.getToken()));
		user.setPassword(bCryptPasswordEncoder.encode(resetPassword.getConfirmPassword()));
	}

	@Override
	public void forgetPassword(ForgetPassword forgetPassword) {
		User user = userRepository.findByEmail(forgetPassword.getEmail())
				.orElseThrow(() -> new ServiceException(ErrorCode.TOKEN_NOT_INVALID, forgetPassword.getEmail()));
		user.setToken(UUID.randomUUID().toString());
	}

}
