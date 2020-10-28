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
import com.example.afcs.bean.AgentLoginRequest;
import com.example.afcs.bean.ChangePasswordRequest;
import com.example.afcs.bean.DriverCondLoginRequest;
import com.example.afcs.bean.LogOutRequest;
import com.example.afcs.bean.LoginRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.SingleJourneyRequest;
import com.example.afcs.dao.UserEntityDAO;
import com.example.afcs.model.DriverConductorEntity;
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
		UserEntity userEntity=null;
		String userName = loginRequest.getUserId();
		String userPassword = loginRequest.getPwd();
		String mobile = loginRequest.getMobile();
		List<UserEntity> loginResponseList = new ArrayList<UserEntity>();
		
		if(userName!=null) {
		userEntity = getUserProfile(userName); // User name must be unique in our system
		}else {
			userEntity = userEntityDAO.findByMobile(mobile);
		}

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

		if (userEntity!=null) {
			if ("Customer".equalsIgnoreCase(userEntity.getUserRole())) {
				if ((userPassword != null && userPassword.equalsIgnoreCase(userEntity.getUserPassword()))
						&&(mobile != null && mobile.equalsIgnoreCase(userEntity.getMobile())) ){
					if ("Verified".equalsIgnoreCase(userEntity.getMobileVerificationStatus()))
						{
						authenticated = true;
						String token = generateNewToken();
						loginRequest.setToken(token);
						userEntity.setToken(token);
						userEntity = updateUser(userEntity);
						/*loginRequest.setStatus("Mobile Verified");
						loginRequest.setPwd(null);
						loginRequest.setId(String.valueOf(userEntity.getId()));
						loginRequest.setFirstName(userEntity.getFirstName());
						loginRequest.setLastName(userEntity.getLastName());
						loginRequest.setEmailId(userEntity.getEmailId());
						loginRequest.setMobile(userEntity.getMobile());
						*/
						
						//loginResponseList.add(loginRequest);
						loginResponseList.add(userEntity);
						afcsApiResponse.setPayloadObj(loginResponseList);
						afcsApiResponse.setResMessage("Login Succesfull");
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
						// loginRequest.setStations(stationMap);
					}else {
						loginRequest.setStatus("Failed");
						loginRequest.setPwd(null);
						afcsApiResponse.setPayloadObj(loginResponseList);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						afcsApiResponse.setResMessage("Mobile not verified");
					}
				} else {
					loginRequest.setStatus("Failed");
					loginRequest.setPwd(null);
					afcsApiResponse.setPayloadObj(loginResponseList);
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
					afcsApiResponse.setResMessage("Invalid Credentials");

				}
			}  
		}else {
			loginRequest.setStatus("Failed");
			loginRequest.setPwd(null);
			afcsApiResponse.setPayloadObj(loginResponseList);
			afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
			afcsApiResponse.setResMessage("User not Exist");
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
	

	@Override
	public AfcsApiResponse loginAgent(AgentLoginRequest agentLoginRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		UserEntity userEntity=null;
		String userId = agentLoginRequest.getUserId();
		String userPassword = agentLoginRequest.getPassword();
		
		List<AgentLoginRequest> loginResponseList = new ArrayList<AgentLoginRequest>();
		

		String secureUserPassword = null;
		boolean authenticated = false;
		
		if(userId!=null) {
		userEntity = getUserProfile(userId); // User name must be unique in our system
		}
		
			if (userPassword != null && userPassword.equalsIgnoreCase(userEntity.getUserPassword())
				&& (userId != null && userId.equalsIgnoreCase(userEntity.getEmailId()))) {
			authenticated = true;
			String token = generateNewToken();
			agentLoginRequest.setToken(token);
			userEntity.setToken(token);
			userEntity = updateUser(userEntity);
			agentLoginRequest.setStatus("Success");
			agentLoginRequest.setUserId(userId);
			agentLoginRequest.setFirstName(userEntity.getFirstName());
			agentLoginRequest.setLastName(userEntity.getLastName());
			agentLoginRequest.setMobile(userEntity.getMobile());
			
			// static data start-
			
			agentLoginRequest.setTerminalId(agentLoginRequest.getTerminalId());
			agentLoginRequest.setTerminalStatus("Verified");
			agentLoginRequest.setStationId("23");
			agentLoginRequest.setStationName("Dwarka Sec21");
			
			//static data end-
			
			agentLoginRequest.setPassword(null);
			loginResponseList.add(agentLoginRequest);
			afcsApiResponse.setPayloadObj(loginResponseList);
			afcsApiResponse.setResMessage("Login Succesfull");
			afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			// loginRequest.setStations(stationMap);

		} else {
				agentLoginRequest.setStatus("Failed");
				agentLoginRequest.setPassword(null);
				afcsApiResponse.setPayloadObj(loginResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("Invalid Credentials");

			}
		
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
		if(!"Y".equalsIgnoreCase(changePasswordRequest.getFpFlag())) {
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
		}else {
			userEntity.setUserPassword(changePasswordRequest.getNewPassword());
			userEntity = updateUser(userEntity);
			if(userEntity.getId()!=null) {
				afcsApiResponse.setResMessage("Password Changed Succesfully");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			}
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
				userEntity.setMobileVerificationStatus("Verified");;
				userEntity = updateUser(userEntity);
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

	@Override
	public AfcsApiResponse logOut(LogOutRequest logOutRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		UserEntity userEntity= getUserProfile(logOutRequest.getUserId());
		userEntity.setToken(null);
		userEntity = updateUser(userEntity);
		afcsApiResponse.setResMessage("Successfully Logout");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		
		return afcsApiResponse;
	}

	
	

	@Override
	public AfcsApiResponse loginDriverCond(DriverCondLoginRequest driverCondLoginRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<DriverConductorEntity> driverConductorList = new ArrayList<DriverConductorEntity>(); 
		DriverConductorEntity driverConductorEntity= new DriverConductorEntity();
		driverConductorEntity.setUserId("41");
		driverConductorEntity.setEmpCode("E0905");
		driverConductorEntity.setFirstName("Subhash");
		driverConductorEntity.setLastName("Singh");
		driverConductorEntity.setRole("Driver");
		driverConductorEntity.setDepotId("D013");
		driverConductorEntity.setDepotName("SN Depot");
		driverConductorEntity.setBusAssigned("DL1R0923");
		driverConductorEntity.setEmaild("subhash@gmail.com");
		driverConductorEntity.setMobile(driverCondLoginRequest.getMobile());
		driverConductorEntity.setStatus("Permanent");
		driverConductorEntity.setMpin(null);
	         
		driverConductorList.add(driverConductorEntity);
		
		afcsApiResponse.setPayloadObj(driverConductorList);
		afcsApiResponse.setResMessage("Login Successful");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		return afcsApiResponse;
	}


}
