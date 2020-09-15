package com.example.afcs.service.impl;

import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.apache.tomcat.websocket.AuthenticationException;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.afcs.bean.AfcsApiRequest;
import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.ChangePasswordRequest;
import com.example.afcs.bean.LoginRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.SingleJourneyRequest;
import com.example.afcs.dao.UserEntityDAO;
import com.example.afcs.model.UserEntity;

import com.example.afcs.service.LoginService;
import com.example.afcs.util.AFCSConstants;
import com.example.afcs.util.AuthenticationUtil;

@Service("loginService")
@ComponentScan("com.example.afcs")
public class LoginServiceImpl implements LoginService {

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	AuthenticationUtil authenticationUtil;

	@Autowired
	UserEntityDAO userEntityDAO;

	@Autowired
	public LoginServiceImpl(AuthenticationUtil authenticationUtil) {
		// this.database = database;
		this.authenticationUtil = authenticationUtil;
	}

	@Override
	public AfcsApiResponse login(LoginRequest loginRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		String userName = loginRequest.getUserId();
		String userPassword = loginRequest.getPwd();
		List<LoginRequest> loginResponseList = new ArrayList<LoginRequest>();
		UserEntity userEntity = getUserProfile(userName); // User name must be unique in our system

		String secureUserPassword = null;
		boolean authenticated = false;
		/*
		 * try { secureUserPassword = authenticationUtil.
		 * generateSecurePassword(userPassword, userEntity.getSalt()); } catch
		 * (InvalidKeySpecException ex) {
		 * //log.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null,
		 * ex); try { throw new AuthenticationException(ex.getLocalizedMessage()); }
		 * catch (AuthenticationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * boolean authenticated = false; if (secureUserPassword != null &&
		 * secureUserPassword.equalsIgnoreCase(userEntity.getUserPassword())) { if
		 * (userName != null && userName.equalsIgnoreCase(userEntity.getUserName())) {
		 * authenticated = true; } }
		 */

		if ("Customer".equalsIgnoreCase(userEntity.getUserRole())) {
			if (userPassword != null && userPassword.equalsIgnoreCase(userEntity.getUserPassword())) {
				if (userName != null && userName.equalsIgnoreCase(userEntity.getEmailId())) {
					authenticated = true;
					String token = generateNewToken();
					loginRequest.setToken(token);
					userEntity.setToken(token);
					userEntity = updateUser(userEntity);
					loginRequest.setStatus("Success");
					loginRequest.setPwd(null);
					loginResponseList.add(loginRequest);
					afcsApiResponse.setPayloadObj(loginResponseList);
					afcsApiResponse.setResMessage("Login Succesfull");
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					// loginRequest.setStations(stationMap);
				}
			} else {
				loginRequest.setStatus("Failed");
				loginRequest.setPwd(null);
				afcsApiResponse.setPayloadObj(loginResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("Failed");

			} 
		}else {
			if (userPassword != null && userPassword.equalsIgnoreCase(userEntity.getUserPassword())) {
				if (userName != null && userName.equalsIgnoreCase(userEntity.getEmailId())) {
					authenticated = true;
					String deviceId = loginRequest.getDeviceId();
					
					
					String token = generateNewToken();
					loginRequest.setToken(token);
					userEntity.setToken(token);
					userEntity = updateUser(userEntity);
					loginRequest.setStatus("Success");
					loginRequest.setPwd(null);
					loginResponseList.add(loginRequest);
					afcsApiResponse.setPayloadObj(loginResponseList);
					afcsApiResponse.setResMessage("Login Succesfull");
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					// loginRequest.setStations(stationMap);
				}
			} else {
				loginRequest.setStatus("Failed");
				loginRequest.setPwd(null);
				afcsApiResponse.setPayloadObj(loginResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("Failed");

			}
		}
		if (!authenticated) {
			try {
				throw new Exception("Authentication failed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//BeanUtils.copyProperties(userEntity, userProfile);

		

		return afcsApiResponse;
	}

	public UserEntity getUserProfile(String userName) {
		UserEntity userEntity = null;
		try {
			userEntity = userEntityDAO.getUserProfile(userName);
		} catch (Exception e) {
		}
		return userEntity;
	}

	
	public UserEntity updateUser(UserEntity userEntity) {
		try{
			userEntity = userEntityDAO.updateUser(userEntity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return userEntity;
	}
	

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}

	@Override
	public AfcsApiResponse changePassword(ChangePasswordRequest changePasswordRequest) {
		AfcsApiResponse afcsApiResponse=new AfcsApiResponse();
		UserEntity userEntity= getUserProfile(changePasswordRequest.getUserId());
		if(userEntity != null && userEntity.getUserPassword().equals(changePasswordRequest.getOldPassword())) {
			userEntity.setUserPassword(changePasswordRequest.getNewPassword());
			userEntity = updateUser(userEntity);
			if(userEntity.getId()!=null) {
				afcsApiResponse.setResMessage("Password Changed Succesfully");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			}
			
		}else {
			afcsApiResponse.setResMessage("Old Password not correct");
			afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
		}
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse verifyMobile(AfcsApiRequest afcsApiRequest,MobileVerificationRequest mobileVerificationRequest) {
		
		UserEntity userEntity=null;
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			userEntity = userEntityDAO.getUserByToken(afcsApiRequest.getTokenId());
			
			if (userEntity.getMobileOtp().equalsIgnoreCase(mobileVerificationRequest.getOtp())) {
				afcsApiResponse.setResMessage("Mobile Verification Successful");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			}else {
				afcsApiResponse.setResMessage("WRONG OTP ");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			afcsApiResponse.setResMessage("Invalid Auth Key");
			afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
			return afcsApiResponse;
			
		}
		
		return afcsApiResponse;
	}

}
