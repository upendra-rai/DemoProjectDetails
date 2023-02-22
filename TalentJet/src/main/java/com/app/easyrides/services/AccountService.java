package com.app.easyrides.services;

import com.app.easyrides.dtos.ForgetPassword;
import com.app.easyrides.dtos.ResetPassword;
import com.app.easyrides.entities.User;

public interface AccountService {

	void registerAccount(User user);

	void resetPassword(ResetPassword resetPassword);

	void forgetPassword(ForgetPassword forgetPassword);
	
	

}
