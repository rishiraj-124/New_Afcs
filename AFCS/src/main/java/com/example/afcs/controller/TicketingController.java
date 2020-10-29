package com.example.afcs.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.afcs.bean.AfcsApiRequest;
import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.AgentLoginRequest;
import com.example.afcs.bean.ChangePasswordRequest;
import com.example.afcs.bean.CustomerRegistrationRequest;
import com.example.afcs.bean.DriverCondLoginRequest;
import com.example.afcs.bean.DriverCondMasterRequest;
import com.example.afcs.bean.Dummy;
import com.example.afcs.bean.ForgetPasswordRequest;
import com.example.afcs.bean.IssueValueQRRequest;
import com.example.afcs.bean.LogOutRequest;
import com.example.afcs.bean.LoginRequest;
import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.MultipleQRCodeUploadRequest;
import com.example.afcs.bean.MyTicketRequest;
import com.example.afcs.bean.MyTripsRequest;
import com.example.afcs.bean.PassFareRequest;
import com.example.afcs.bean.PassQRRequest;
import com.example.afcs.bean.PassSerialNumRequest;
import com.example.afcs.bean.QRPassRequest;
import com.example.afcs.bean.ScanQRTicketRequest;
import com.example.afcs.bean.ShiftRepoSummRequest;
import com.example.afcs.bean.SingleJourneyRequest;
import com.example.afcs.bean.SubmitTicketRequest;
import com.example.afcs.bean.TicketDataValidationRequest;
import com.example.afcs.bean.TicketFareRequest;
import com.example.afcs.bean.UploadScannedQRCodeRequest;
import com.example.afcs.bean.UploadTrxnDataRequest;
import com.example.afcs.bean.ValidationBean;
import com.example.afcs.main.ApplicationPropertyReader;
import com.example.afcs.model.UserEntity;
import com.example.afcs.service.LoginService;
import com.example.afcs.service.TicketingService;
import com.example.afcs.util.AFCSConstants;
import com.example.afcs.util.AFCSUtil;
import com.example.afcs.util.ExceptionUtils;
import com.example.afcs.util.KeyEncryptionUtils;


@Controller
@RequestMapping("/ticketing")
@ComponentScan({"com.example.afcs.bean","com.example.afcs.service"})
public class TicketingController {
	
	static {
		System.out.println("********************");
	}
	
	private static final Logger log = LoggerFactory.getLogger(TicketingController.class);
	@Autowired
	LoginRequest loginRequest;
	
	@Autowired
	LoginService loginService;
	
	@Autowired 
	TicketingService ticketingService;
	 
	
	
	@Autowired
	private ApplicationPropertyReader applicationPropertyReader;
	 
	
	

	
	
	@PostMapping(path="/custlogin",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse login(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			
			Object payloadObj = afcsApiRequest.getPayloadObj();
			LoginRequest loginRequest = AFCSUtil.jsonStringToObject(payloadObj,
					LoginRequest.class);
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(loginRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				//loginRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String errormsg="";
				if(errormsg.equals("")){
					afcsApiResponse = loginService.login(loginRequest);
				
				}else{
					loginRequest.setErrorMsg(errormsg);
				}
				

			}
			log.info("loginResponse--->> " + loginRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/agentlogin",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse agentlogin(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			
			Object payloadObj = afcsApiRequest.getPayloadObj();
			AgentLoginRequest agentLoginRequest = AFCSUtil.jsonStringToObject(payloadObj, AgentLoginRequest.class);
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(agentLoginRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				//loginRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String errormsg="";
				if(errormsg.equals("")){
					afcsApiResponse = loginService.loginAgent(agentLoginRequest);
				}else{
					loginRequest.setErrorMsg(errormsg);
				}
				

			}
			log.info("loginResponse--->> " + loginRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/qrSJTicket",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse singleJourney(@RequestBody AfcsApiRequest afcsApiRequest , HttpServletRequest request) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			String errormsg="";
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			Object payloadObj = afcsApiRequest.getPayloadObj();
			SingleJourneyRequest singleJourneyRequest = AFCSUtil.jsonStringToObject(payloadObj,
					SingleJourneyRequest.class);
		
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(singleJourneyRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				//singleJourneyRequest.setErrorMsg(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {

				// String token = request.getHeader("tokenId");
				String token = afcsApiRequest.getTokenId();
				if (singleJourneyRequest != null) {
					String userId = singleJourneyRequest.getUserId();
					UserEntity userEntity = loginService.getUserProfile(userId);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						singleJourneyRequest.setErrorMsg(errormsg);
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.INVALID_AUTH_KEY);
						return afcsApiResponse;
					}
					
					

				}
				if (errormsg.equals("")) {
					afcsApiResponse = ticketingService.singleJourneyFare(singleJourneyRequest);
				}

			}
			log.info("SingleJourney--->> " + singleJourneyRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/submitTicketInfo",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse submitTicketInfo(@RequestBody AfcsApiRequest afcsApiRequest , HttpServletRequest request) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			String errormsg="";
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//Set<ConstraintViolation<ValidationBean>> validationError1 = KeyEncryptionUtils.validateBean(afcsApiRequest);
			Object payloadObj = afcsApiRequest.getPayloadObj();
			SubmitTicketRequest submitTicketRequest = AFCSUtil.jsonStringToObject(payloadObj,
					SubmitTicketRequest.class);
		
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(submitTicketRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				//singleJourneyRequest.setErrorMsg(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {

				// String token = request.getHeader("tokenId");
				String token = afcsApiRequest.getTokenId();
				if (submitTicketRequest != null) {
					String userId = submitTicketRequest.getUserId();
					UserEntity userEntity = loginService.getUserProfile(userId);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.INVALID_AUTH_KEY);
						return afcsApiResponse;
					}

				}
				if (errormsg.equals("")) {
					afcsApiResponse = ticketingService.getTktSerialNo(submitTicketRequest);
				}

			}
			log.info("SubmitTicketInfo--->> " + submitTicketRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	
	@PostMapping(path="/custReg",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse custReg(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = null;
		try {
			log.info("afcsApiRequest--->> " + afcsApiRequest.toString());
			String channelId = request.getHeader("channelId");

			Object payloadObj = afcsApiRequest.getPayloadObj();

			CustomerRegistrationRequest customerRegistrationRequest = AFCSUtil.jsonStringToObject(payloadObj,
					CustomerRegistrationRequest.class);

			System.out.println();
			// System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils
					.validateBean(customerRegistrationRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setResSatus(300);
				afcsApiResponse.setResMessage(errorMessage);
				// customerRegistrationRequest.setErrorMsg(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS + request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String errormsg = "";
				if (errormsg.equals("")) {
					afcsApiResponse = ticketingService.custReg(customerRegistrationRequest);
				} else {

				}

			}
			log.info("customerRegistrationResponse--->> " + afcsApiResponse.toString());

		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(),
					AFCSConstants.LOGGING_METHOD_NAME + Thread.currentThread().getStackTrace()[1].getMethodName()
							+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
							+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
							+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2] + AFCSConstants.LOGGING_LINE_NUMBER
							+ exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/myProfile",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse myProfile(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("afcsApiRequest--->> " + afcsApiRequest.toString());
			String channelId = request.getHeader("channelId");

			Object payloadObj = afcsApiRequest.getPayloadObj();

			CustomerRegistrationRequest customerRegistrationRequest = AFCSUtil.jsonStringToObject(payloadObj,
					CustomerRegistrationRequest.class);

			System.out.println();
			// System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils
					.validateBean(customerRegistrationRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setResSatus(300);
				afcsApiResponse.setResMessage(errorMessage);
				// customerRegistrationRequest.setErrorMsg(errorMessage);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS + request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String errormsg="";
				String token = afcsApiRequest.getTokenId();

				if (customerRegistrationRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						userEntity.setFirstName(customerRegistrationRequest.getFirstName());
						userEntity.setLastName(customerRegistrationRequest.getLastName());
						userEntity.setEmailId(customerRegistrationRequest.getEmail());
						userEntity.setAddress1(customerRegistrationRequest.getAddress1());
						userEntity.setAddress2(customerRegistrationRequest.getAddress2());
						userEntity.setCity(customerRegistrationRequest.getCity());
						userEntity.setState(customerRegistrationRequest.getState());
						userEntity.setPincode(customerRegistrationRequest.getPincode());
						userEntity.setImei(customerRegistrationRequest.getImei());
						userEntity.setIpAddress(customerRegistrationRequest.getIpAddress());
						userEntity = loginService.updateUser(userEntity);
						if(userEntity.getId()!=null) {
							afcsApiResponse.setPayloadObj(null);
							afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
							afcsApiResponse.setResMessage("User updated Successfully");
						}
					}
				}				

			

			}
			log.info("customerRegistrationResponse--->> " + afcsApiResponse.toString());

		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(),
					AFCSConstants.LOGGING_METHOD_NAME + Thread.currentThread().getStackTrace()[1].getMethodName()
							+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
							+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
							+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2] + AFCSConstants.LOGGING_LINE_NUMBER
							+ exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/getMasterData",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse getMasterData(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			MasterDataRequest masterDataRequest = AFCSUtil.jsonStringToObject(payloadObj,
					MasterDataRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(masterDataRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				masterDataRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(masterDataRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				if(masterDataRequest!=null) {
					String userId = masterDataRequest.getUserId();
					UserEntity userEntity= loginService.getUserProfile(userId);
					if(!userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg+="Invalid Auth Key";
						masterDataRequest.setErrorMsg(errormsg);
						afcsApiResponse.setPayloadObj(masterDataRequest);
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}
					
					
				}
				
				if(errormsg.equals("")){
					afcsApiResponse = ticketingService.getMasterData(masterDataRequest);
				}else{
					masterDataRequest.setErrorMsg(errormsg);
				}

			}
			log.info("AfcsApiResponse--->> " + masterDataRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/changePwd",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse changePwd(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			ChangePasswordRequest changePasswordRequest = AFCSUtil.jsonStringToObject(payloadObj,
					ChangePasswordRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(changePasswordRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				changePasswordRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(changePasswordRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				if(changePasswordRequest!=null) {
					String userId = changePasswordRequest.getUserId();
					UserEntity userEntity= loginService.getUserProfile(userId);
					if(!userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg+="Invalid Auth Key";
						changePasswordRequest.setErrorMsg(errormsg);
						afcsApiResponse.setPayloadObj(changePasswordRequest);
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}
					
					
				}
				
				if(errormsg.equals("")){
					afcsApiResponse = loginService.changePassword(changePasswordRequest);
				}else{
					changePasswordRequest.setErrorMsg(errormsg);
				}

			}
			log.info("AfcsApiResponse--->> " + changePasswordRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/forgotPwd",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse forgotPwd(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			ForgetPasswordRequest forgetPasswordRequest = AFCSUtil.jsonStringToObject(payloadObj,
					ForgetPasswordRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(forgetPasswordRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				
				afcsApiResponse.setPayloadObj(forgetPasswordRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				if(forgetPasswordRequest!=null) {
					String userId = forgetPasswordRequest.getUserId();
					UserEntity userEntity= loginService.getUserProfile(userId);
					if(!userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg+="Invalid Auth Key";
						afcsApiResponse.setPayloadObj(forgetPasswordRequest);
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						//need to send OTP SMS on mobile for verification.
						userEntity.setMobileOtp(AFCSUtil.getRandomNum(6));
						userEntity.setMobileVerificationStatus(null);
						token = AFCSUtil.generateNewToken();
						userEntity.setToken(token);
						userEntity = loginService.updateUser(userEntity);
						
						List<UserEntity> responseList = new ArrayList<UserEntity>();
						UserEntity userEntity2= new UserEntity();
						userEntity2.setMobileOtp(userEntity.getMobileOtp());
						userEntity2.setEmailId(userEntity.getEmailId());
						userEntity2.setToken(userEntity.getToken());
						
						responseList.add(userEntity2);
						afcsApiResponse.setPayloadObj(responseList);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
						afcsApiResponse.setResMessage("OTP sent to your mobile no.");
						
					}
					
					
				}
				
				/*if(errormsg.equals("")){
					forgetPasswordRequest.setOtp(AFCSUtil.getRandomNum(6));
					afcsApiResponse = loginService.changePassword(forgetPasswordRequest);
				}else{
					//forgetPasswordRequest.setErrorMsg(errormsg);
				}*/

			}
			log.info("AfcsApiResponse--->> " + forgetPasswordRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/scanQRTicket",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse scanQRTicket(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			ScanQRTicketRequest scanQRTicketRequest = AFCSUtil.jsonStringToObject(payloadObj,
					ScanQRTicketRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(scanQRTicketRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				scanQRTicketRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(scanQRTicketRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				/*
				 * if(changePasswordRequest!=null) { String userId =
				 * changePasswordRequest.getUserId(); UserEntity userEntity=
				 * loginService.getUserProfile(userId);
				 * if(!userEntity.getToken().equalsIgnoreCase(token)) {
				 * errormsg+="Invalid Auth Key"; changePasswordRequest.setErrorMsg(errormsg);
				 * afcsApiResponse.setPayloadObj(changePasswordRequest);
				 * afcsApiResponse.setResMessage(errormsg);
				 * afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED); return
				 * afcsApiResponse; }
				 * 
				 * 
				 * }
				 */
				
				if(errormsg.equals("")){
					afcsApiResponse = ticketingService.scanQRCode(scanQRTicketRequest);
				}else{
					scanQRTicketRequest.setErrorMsg(errormsg);
				}

			}
			log.info("AfcsApiResponse--->> " + scanQRTicketRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/qrPassTicket",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse qrPassTicket(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			QRPassRequest qrPassRequest = AFCSUtil.jsonStringToObject(payloadObj,
					QRPassRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(qrPassRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				qrPassRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(qrPassRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				/*
				 * if(changePasswordRequest!=null) { String userId =
				 * changePasswordRequest.getUserId(); UserEntity userEntity=
				 * loginService.getUserProfile(userId);
				 * if(!userEntity.getToken().equalsIgnoreCase(token)) {
				 * errormsg+="Invalid Auth Key"; changePasswordRequest.setErrorMsg(errormsg);
				 * afcsApiResponse.setPayloadObj(changePasswordRequest);
				 * afcsApiResponse.setResMessage(errormsg);
				 * afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED); return
				 * afcsApiResponse; }
				 * 
				 * 
				 * }
				 */
				
				if(errormsg.equals("")){
					afcsApiResponse = ticketingService.qrPassTicket(qrPassRequest);
				}else{
					qrPassRequest.setErrorMsg(errormsg);
				}

			}
			log.info("AfcsApiResponse--->> " + qrPassRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/issueValueQR",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse issueValueQR(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			IssueValueQRRequest issueValueQRRequest = AFCSUtil.jsonStringToObject(payloadObj,
					IssueValueQRRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(issueValueQRRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				issueValueQRRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(issueValueQRRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token=afcsApiRequest.getTokenId();
				/*
				 * if(changePasswordRequest!=null) { String userId =
				 * changePasswordRequest.getUserId(); UserEntity userEntity=
				 * loginService.getUserProfile(userId);
				 * if(!userEntity.getToken().equalsIgnoreCase(token)) {
				 * errormsg+="Invalid Auth Key"; changePasswordRequest.setErrorMsg(errormsg);
				 * afcsApiResponse.setPayloadObj(changePasswordRequest);
				 * afcsApiResponse.setResMessage(errormsg);
				 * afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED); return
				 * afcsApiResponse; }
				 * 
				 * 
				 * }
				 */
				
				if(errormsg.equals("")){
					afcsApiResponse = ticketingService.issueValueQR(issueValueQRRequest);
				}else{
					issueValueQRRequest.setErrorMsg(errormsg);
				}

			}
			log.info("AfcsApiResponse--->> " + issueValueQRRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/mobileVerification",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse mobileVerification(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			MobileVerificationRequest mobileVerificationRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					MobileVerificationRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(mobileVerificationRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				mobileVerificationRequest.setErrorMsg(errorMessage);
				afcsApiResponse.setPayloadObj(mobileVerificationRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (mobileVerificationRequest != null) {
					afcsApiResponse= loginService.verifyMobile(afcsApiRequest ,mobileVerificationRequest);
				}				

			}
			log.info("AfcsApiResponse--->> " + mobileVerificationRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/getMyTrips",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse getMyTrips(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			MyTripsRequest myTripsRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					MyTripsRequest.class);

			
			String errormsg="";
			//String token = request.getHeader("tokenId");
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(myTripsRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(myTripsRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (myTripsRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getMyTrips(myTripsRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + myTripsRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/getTicketFare",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse getTicketFare(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			TicketFareRequest ticketFareRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					TicketFareRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(ticketFareRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(ticketFareRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (ticketFareRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getFare(ticketFareRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + ticketFareRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/getShftRepoSumm",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse getShftRepoSumm(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			ShiftRepoSummRequest shiftRepoSummRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					ShiftRepoSummRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(shiftRepoSummRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(shiftRepoSummRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (shiftRepoSummRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getShiftSumm(shiftRepoSummRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + shiftRepoSummRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/getShftRepoDetail",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse getShftRepoDetail(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			ShiftRepoSummRequest shiftRepoSummRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					ShiftRepoSummRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(shiftRepoSummRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(shiftRepoSummRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (shiftRepoSummRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getShiftDetail(shiftRepoSummRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + shiftRepoSummRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/myTicket",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse myTicket(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			MyTicketRequest myTicketRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					MyTicketRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(myTicketRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(myTicketRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (myTicketRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getMyValidTicket(myTicketRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + myTicketRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/passFare",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse passFare(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			PassFareRequest passFareRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					PassFareRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(passFareRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(passFareRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (passFareRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
					if (userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}else {
						afcsApiResponse = ticketingService.getPassDetails(passFareRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + passFareRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/passSerialNum",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse passSerialNum(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			PassSerialNumRequest passSerialNumRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					PassSerialNumRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(passSerialNumRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(passSerialNumRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (passSerialNumRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}
					else {
						afcsApiResponse = ticketingService.getPassSerialNumber(passSerialNumRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + passSerialNumRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/passQR",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse passQR(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			PassQRRequest passQRRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					PassQRRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(passQRRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(passQRRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (passQRRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}
					else {
						afcsApiResponse = ticketingService.getPassQRCode(passQRRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + passQRRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	@PostMapping(path="/logOut",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse logOut(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			LogOutRequest logOutRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					LogOutRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(logOutRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(logOutRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (logOutRequest != null) {
					UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}
					else {
						afcsApiResponse = loginService.logOut(logOutRequest);
					}
				}				

			}
			log.info("AfcsApiResponse--->> " + logOutRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/driverCondLogin",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse driverCondLogin(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			DriverCondLoginRequest driverCondLoginRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					DriverCondLoginRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(driverCondLoginRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(driverCondLoginRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (driverCondLoginRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = loginService.loginDriverCond(driverCondLoginRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + driverCondLoginRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/driverCondMaster",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse driverCondMaster(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			DriverCondMasterRequest driverCondMasterRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					DriverCondMasterRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(driverCondMasterRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(driverCondMasterRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (driverCondMasterRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = ticketingService.getDriverConductorMasterData(driverCondMasterRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + driverCondMasterRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/ticketDataValidation",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse ticketDataValidation(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			TicketDataValidationRequest ticketDataValidationRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					TicketDataValidationRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(ticketDataValidationRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(ticketDataValidationRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (ticketDataValidationRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = ticketingService.getValidatedTicketData(ticketDataValidationRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + ticketDataValidationRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/uploadScannedQRCode",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse uploadScannedQRCode(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();

			UploadScannedQRCodeRequest uploadScannedQRCodeRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					UploadScannedQRCodeRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(uploadScannedQRCodeRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(uploadScannedQRCodeRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (uploadScannedQRCodeRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = ticketingService.uploadQRCode(uploadScannedQRCodeRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + uploadScannedQRCodeRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/multipleQRCodeUpload",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse multipleQRCodeUpload(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();
			
			MultipleQRCodeUploadRequest multipleQRCodeUploadRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					MultipleQRCodeUploadRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(multipleQRCodeUploadRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(multipleQRCodeUploadRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (multipleQRCodeUploadRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = ticketingService.multipleQRCodeUpload(multipleQRCodeUploadRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + multipleQRCodeUploadRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	@PostMapping(path="/uploadTrxnData",consumes="application/json",produces="application/json")
	@ResponseBody
	public AfcsApiResponse uploadTrxnData(@RequestBody AfcsApiRequest afcsApiRequest, HttpServletRequest request) {

		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.info("AfcsApiRequest--->> " + afcsApiRequest.toString());
			//String channelId = request.getHeader("channelId");
			
			Object payloadObj = afcsApiRequest.getPayloadObj();
			
			UploadTrxnDataRequest uploadTrxnDataRequest =  AFCSUtil.jsonStringToObject(payloadObj,
					UploadTrxnDataRequest.class);

			
			String errormsg="";
			
			
			System.out.println();
			//System.out.println(applicationPropertyReader.getEncodingList()+"----------------------"+applicationPropertyReader.getHostList());
			Set<ConstraintViolation<ValidationBean>> validationError = KeyEncryptionUtils.validateBean(uploadTrxnDataRequest);

			if (validationError != null && !validationError.isEmpty()) {

				String errorMessage = getErrorMessage(validationError);
				afcsApiResponse.setPayloadObj(uploadTrxnDataRequest);
				afcsApiResponse.setResMessage(errorMessage);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				log.info(this.getClass().getSimpleName(),
						AFCSConstants.LOGGING_RESPONSE + AFCSConstants.LOGGING_METHOD_NAME
								+ Thread.currentThread().getStackTrace()[1].getMethodName()
								+ AFCSConstants.LOGGING_REQUEST_FROM + request.getRemoteAddr()
								+ AFCSConstants.LOGGING_USER_ADDRESS
								+ request.getHeader(AFCSConstants.USER_IP_ADDRESS)
								+ AFCSConstants.LOGGING_ERROR + errorMessage);

				return afcsApiResponse;

			} else {
				String token = afcsApiRequest.getTokenId();

				if (uploadTrxnDataRequest != null) {
				/*	UserEntity userEntity = ticketingService.getUserByToken(token);
						
					if(userEntity ==null || !userEntity.getToken().equalsIgnoreCase(token)){
						errormsg += "Invalid Auth Key";
						afcsApiResponse.setResMessage(errormsg);
						afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
						return afcsApiResponse;
					}*/
					
						afcsApiResponse = ticketingService.uploadTicketTxnData(uploadTrxnDataRequest);
					
				}				

			}
			log.info("AfcsApiResponse--->> " + uploadTrxnDataRequest.toString());
			
		} catch (Exception e) {

			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					AFCSConstants.PACKAGE_FOR_EXCEPTION);

			log.info(this.getClass().getSimpleName(), AFCSConstants.LOGGING_METHOD_NAME
					+ Thread.currentThread().getStackTrace()[1].getMethodName()
					+ AFCSConstants.LOGGING_ERROR_MESSAGE + e + AFCSConstants.LOGGGING_CLASS_NAME
					+ exceptionData[0] + AFCSConstants.LOGGING_FILE_NAME + exceptionData[1]
					+ AFCSConstants.LOGGING_METHOD_NAME + exceptionData[2]
					+ AFCSConstants.LOGGING_LINE_NUMBER + exceptionData[3]);
		}
		return afcsApiResponse;

	}
	
	
	private String getErrorMessage(Set<ConstraintViolation<ValidationBean>> validationError) 
	{
		 Iterator<ConstraintViolation<ValidationBean>>  itr =  validationError.iterator();
		 StringBuilder builder = new StringBuilder();
		 
		 int counter = 1;
		 while(itr.hasNext())
		 {
			 builder.append(counter+". "+itr.next().getMessage()+" ");
			 
			 counter++;
		 }
		 
		return builder.toString();
	}
	
	@GetMapping("/test")
	public @ResponseBody String getTest() {
		return "hello";
	}
	@PostMapping("/test")
	public  @ResponseBody Dummy<CustomerRegistrationRequest> getDummy(@RequestBody Dummy<CustomerRegistrationRequest> dummy) {
		
		System.out.println(dummy.getChannelId());
		System.out.println(dummy.getTokenId());
		System.out.println(dummy.getPayload());
		return dummy;
	}

	

}

