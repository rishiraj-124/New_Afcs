package com.example.afcs.service;


import com.example.afcs.bean.AfcsApiRequest;
import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.ChangePasswordRequest;
import com.example.afcs.bean.LoginRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.model.UserEntity;

public interface LoginService {
	
	public AfcsApiResponse login(LoginRequest loginRequest);
	
	
	public AfcsApiResponse changePassword(ChangePasswordRequest ChangePasswordRequest);
	
	
	public UserEntity getUserProfile(String userName);
	
	public UserEntity updateUser(UserEntity userEntity);
	
	public AfcsApiResponse verifyMobile(AfcsApiRequest afcsApiRequest,MobileVerificationRequest mobileVerificationRequest); 
	
	

}
