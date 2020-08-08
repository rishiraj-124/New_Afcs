package com.example.afcs.dao;

import org.springframework.stereotype.Component;

import com.example.afcs.model.UserEntity;


public interface UserEntityDAO {

	 
	    public UserEntity getUserProfile(String userName);
	    
	    public UserEntity saveUser(UserEntity userEntity);
	    
	    public UserEntity updateUser(UserEntity userEntity);
	    
	    public UserEntity findByMobile(String mobile);
	    
	    public UserEntity getUserByToken(String token);
}
