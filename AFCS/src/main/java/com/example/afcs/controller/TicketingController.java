package com.example.afcs.controller;



import java.util.Iterator;
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
import com.example.afcs.bean.ChangePasswordRequest;
import com.example.afcs.bean.CustomerRegistrationRequest;
import com.example.afcs.bean.Dummy;
import com.example.afcs.bean.IssueValueQRRequest;
import com.example.afcs.bean.LoginRequest;
import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.QRPassRequest;
import com.example.afcs.bean.ScanQRTicketRequest;
import com.example.afcs.bean.SingleJourneyRequest;
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
	 
	
	

	
	
	@PostMapping(path="/login",consumes="application/json",produces="application/json")
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
					if (!userEntity.getToken().equalsIgnoreCase(token)) {
						errormsg += "Invalid Auth Key";
						singleJourneyRequest.setErrorMsg(errormsg);
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

