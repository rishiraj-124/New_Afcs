package com.example.afcs.service.impl;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.CustomerRegistrationRequest;
import com.example.afcs.bean.IssueValueQRRequest;
import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.Passenger;
import com.example.afcs.bean.QRPassRequest;
import com.example.afcs.bean.ScanQRTicketRequest;
import com.example.afcs.bean.SingleJourneyRequest;
import com.example.afcs.dao.JourneyTicketDAO;
import com.example.afcs.dao.MasterDataDAO;
import com.example.afcs.dao.UserEntityDAO;
import com.example.afcs.dao.impl.UserEntityDAOImpl;
import com.example.afcs.model.AfcsGateDeviceMaster;
import com.example.afcs.model.MasterDataEntity;
import com.example.afcs.model.PassMasterEntity;
import com.example.afcs.model.PassengerEntity;
import com.example.afcs.model.QRPassEntity;
import com.example.afcs.model.SingleJourneyEntitiy;
import com.example.afcs.model.UserEntity;
import com.example.afcs.model.ValueQRTicketEntity;
import com.example.afcs.service.TicketingService;
import com.example.afcs.util.AFCSConstants;
import com.example.afcs.util.AFCSUtil;
import com.example.afcs.util.ExceptionUtils;

@Service("ticketingService")
@ComponentScan("com.example.afcs")
public class TicketingServiceImpl implements TicketingService{

	private static final Logger log = LoggerFactory.getLogger(TicketingServiceImpl.class);
	
	
	@Autowired
	UserEntityDAO userEntityDAO;
	
	@Autowired
	JourneyTicketDAO journeyTicketDAO;
	
	@Autowired
	MasterDataDAO masterDataDAO;
	
	
	@Override
	public AfcsApiResponse singleJourneyFare(SingleJourneyRequest singleJourneyRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {

			log.debug("********singleJourneyFare method starts*********");
			List<SingleJourneyRequest> singleJourneyResponseList = null;
			
			SingleJourneyEntitiy singleJourneyEntitiy = new SingleJourneyEntitiy();
			singleJourneyEntitiy.setUserId(singleJourneyRequest.getUserId());
			// String tktbkngdt = singleJourneyRequest.getTktBookingdt();
			Date tktbkngdt = Date.valueOf(singleJourneyRequest.getTktBookingdt());
			singleJourneyEntitiy.setTktBookingdt(tktbkngdt);
			singleJourneyEntitiy.setSrcStnId(singleJourneyRequest.getSrcStnId());
			singleJourneyEntitiy.setDestStnId(singleJourneyRequest.getDestStnId());
			singleJourneyEntitiy.setTktNo(singleJourneyRequest.getTktNo());
			singleJourneyEntitiy.setCustIpAddress(singleJourneyRequest.getCustIpAddress());
			singleJourneyEntitiy.setCustImei(singleJourneyRequest.getCustImei());
			singleJourneyEntitiy.setTktType(singleJourneyRequest.getTktType());
			singleJourneyEntitiy.setPayMode(singleJourneyRequest.getPayMode());
			singleJourneyEntitiy.setPaidAmt(singleJourneyRequest.getPaidAmt());
			singleJourneyEntitiy.setPmtId(singleJourneyRequest.getPmtId());
			singleJourneyEntitiy.setStatus("ACTIVE");
			PassengerEntity passengerEntity;
			List<PassengerEntity> passengerEntities = new ArrayList<PassengerEntity>();
			List<Passenger> psgList = singleJourneyRequest.getPsgList();
			for (Passenger passenger : psgList) {
				passengerEntity = new PassengerEntity(passenger.getPssType(), passenger.getNoOfTkt(),
						passenger.getTktAmount(), passenger.getDiscount(), passenger.getAmtPaid(),
						passenger.getTotalAmt(),singleJourneyEntitiy);
				passengerEntities.add(passengerEntity);
			}
			singleJourneyEntitiy.setPassengers(passengerEntities);
			singleJourneyEntitiy.setQrTicketHash(AFCSUtil.getAlphaNumericString(60));
			singleJourneyEntitiy = journeyTicketDAO.saveJourney(singleJourneyEntitiy);
			if(singleJourneyEntitiy.getId()!=null){
				singleJourneyResponseList = new ArrayList<SingleJourneyRequest>();
				singleJourneyRequest.setTkt_status("Success");
				singleJourneyRequest.setTkt_validity("2hrs");
				singleJourneyRequest.setQrTicketHash(singleJourneyEntitiy.getQrTicketHash());
				singleJourneyResponseList.add(singleJourneyRequest);
				afcsApiResponse.setPayloadObj(singleJourneyResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
				afcsApiResponse.setResMessage("Success");
			}
			

		} catch (Exception e) {
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					"com.example.afcs");
			log.debug("Exception occured: " + this.getClass().getSimpleName() + "method name: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "error message: " + e + "class name: "
					+ exceptionData[0] + "file name: " + exceptionData[1] + "log method name: " + exceptionData[2]
					+ "line number: " + exceptionData[3]);
		}

		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse custReg(CustomerRegistrationRequest custRegReq) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.debug("********custReg method starts*********");
			
			UserEntity userEntity = userEntityDAO.getUserProfile(custRegReq.getEmail());
			
			List<CustomerRegistrationRequest> customerRegistrationResponseList = null;
			if (null != custRegReq && null == userEntity) {
				userEntity = new UserEntity();
				userEntity.setFirstName(custRegReq.getFirstName());
				userEntity.setLastName(custRegReq.getLastName());
				userEntity.setFullName(custRegReq.getFirstName()+" "+custRegReq.getLastName());
				userEntity.setAddress1(custRegReq.getAddress1());
				userEntity.setAddress2(custRegReq.getAddress2());
				userEntity.setCity(custRegReq.getCity());
				userEntity.setState(custRegReq.getState());
				userEntity.setPincode(custRegReq.getPincode());
				userEntity.setCountry(custRegReq.getCountry());
				userEntity.setEmailId(custRegReq.getEmail());
				userEntity.setMobile(custRegReq.getMobile());
				userEntity.setUserPassword(custRegReq.getPassword());
				userEntity.setImei(custRegReq.getImei());
				userEntity.setIpAddress(custRegReq.getIpAddress());
				userEntity.setUserRole("Customer");
				userEntity.setMobileOtp(AFCSUtil.getRandomNum(6));
				userEntity.setToken(generateNewToken());
				userEntity = userEntityDAO.saveUser(userEntity);
				if (userEntity.getId() != null) {
					customerRegistrationResponseList = new ArrayList<CustomerRegistrationRequest>();
					custRegReq.setTokenId(userEntity.getToken());
					custRegReq.setMobileOtp(userEntity.getMobileOtp());
					customerRegistrationResponseList.add(custRegReq);
					afcsApiResponse.setPayloadObj(customerRegistrationResponseList);
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					afcsApiResponse.setResMessage("Successfully Registered and verification Link sent ");

				}
			}else {
				customerRegistrationResponseList = new ArrayList<CustomerRegistrationRequest>();
				afcsApiResponse.setPayloadObj(customerRegistrationResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("EmailId already Exist");
			}

		} catch (Exception e) {
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					"com.example.afcs");
			log.debug("Exception occured: " + this.getClass().getSimpleName() + "method name: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "error message: " + e + "class name: "
					+ exceptionData[0] + "file name: " + exceptionData[1] + "log method name: " + exceptionData[2]
					+ "line number: " + exceptionData[3]);
		}
		return afcsApiResponse;
	}
	
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}

	@Override
	public AfcsApiResponse getMasterData(MasterDataRequest masterDataRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		try {
			log.debug("********getMasterData method starts*********");
			MasterDataEntity masterDataEntity = masterDataDAO.getMasterData();
			if(null!=masterDataEntity) {
				masterDataRequest.setTicketList(masterDataEntity.getTicketList());
				masterDataRequest.setPssngrList(masterDataEntity.getPssngrList());
				masterDataRequest.setStnList(masterDataEntity.getStnList());
				masterDataRequest.setPassList(masterDataEntity.getPassList());
				afcsApiResponse.setPayloadObj(masterDataRequest);
				afcsApiResponse.setResMessage("Success");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			}else {
				afcsApiResponse.setPayloadObj(masterDataRequest);
				afcsApiResponse.setResMessage("Failed");
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
			}
			
		} catch (Exception e) {
			String[] exceptionData = ExceptionUtils.getExceptionGeneratedClassDetails(e.getStackTrace(),
					"com.example.afcs");
			log.debug("Exception occured: " + this.getClass().getSimpleName() + "method name: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + "error message: " + e + "class name: "
					+ exceptionData[0] + "file name: " + exceptionData[1] + "log method name: " + exceptionData[2]
					+ "line number: " + exceptionData[3]);
		}
		
		return afcsApiResponse;
	}
	


	@Override
	public AfcsApiResponse scanQRCode(ScanQRTicketRequest scanQRTicketRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		SingleJourneyEntitiy singleJourneyEntitiy = null;

		try {
			singleJourneyEntitiy = journeyTicketDAO.getSingleJourney(scanQRTicketRequest.getTktQRCode());

			if (singleJourneyEntitiy != null
					&& singleJourneyEntitiy.getQrTicketHash().equals(scanQRTicketRequest.getTktQRCode()) && "ACTIVE".equalsIgnoreCase(singleJourneyEntitiy.getStatus())) {
				AfcsGateDeviceMaster afcsGateDeviceMaster = journeyTicketDAO
						.getGateDeviceId(scanQRTicketRequest.getDeviceId());
				if ("ACTIVE".equalsIgnoreCase(afcsGateDeviceMaster.getStatus())
						&& "Entry gate".equalsIgnoreCase(afcsGateDeviceMaster.getGateType())) {
					singleJourneyEntitiy.setEntry("Yes");
					singleJourneyEntitiy.setEntryStation(afcsGateDeviceMaster.getAtStation());
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					afcsApiResponse.setResMessage("Valid Ticket,Entered Successfully");
				} else if ("ACTIVE".equalsIgnoreCase(afcsGateDeviceMaster.getStatus())
						&& "Exit gate".equalsIgnoreCase(afcsGateDeviceMaster.getGateType()) && "Yes".equalsIgnoreCase(singleJourneyEntitiy.getEntry())) {
					singleJourneyEntitiy.setExit("Yes");
					singleJourneyEntitiy.setExitStation(afcsGateDeviceMaster.getAtStation());
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					afcsApiResponse.setResMessage("Valid Ticket,Exit Successfully");
				}else {
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
					afcsApiResponse.setResMessage("Invalid Exit");
				}

				singleJourneyEntitiy = journeyTicketDAO.updateJourney(singleJourneyEntitiy);
			} else {
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("Invalid Ticket, Please check with Customer-Care");

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			
		}

		return afcsApiResponse;
	} 
	
	@Override
	public AfcsApiResponse issueValueQR(IssueValueQRRequest issueValueQRRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<IssueValueQRRequest> issueValueQRResponseList = null;
		try {
			if(issueValueQRRequest!=null) {
				ValueQRTicketEntity valueQRTicketEntity = new ValueQRTicketEntity();
				valueQRTicketEntity.setAmount(issueValueQRRequest.getAmount());
				valueQRTicketEntity.setPaymentMode(issueValueQRRequest.getPaymentMode());
				valueQRTicketEntity.setImei(issueValueQRRequest.getImei());
				valueQRTicketEntity.setFullTicketNo(AFCSUtil.getRandomNum(20));
				valueQRTicketEntity.setQrTicketHash(AFCSUtil.getAlphaNumericString(60));
				//need to call third party api for  payment gateway status
				valueQRTicketEntity.setPaymentStatus("Success");
				long millis=System.currentTimeMillis();
				java.sql.Date date=new java.sql.Date(millis);
				valueQRTicketEntity.setIssueDate(date);
				valueQRTicketEntity.setIssueTime("");
				valueQRTicketEntity = journeyTicketDAO.saveValueQR(valueQRTicketEntity);
				if(valueQRTicketEntity.getId()!=null){
					issueValueQRResponseList = new ArrayList<IssueValueQRRequest>();
					issueValueQRRequest.setTicketId(String.valueOf(valueQRTicketEntity.getId()));
					issueValueQRRequest.setFullTicketNo(valueQRTicketEntity.getFullTicketNo());
					issueValueQRRequest.setPaymentStatus(valueQRTicketEntity.getPaymentStatus());
					issueValueQRRequest.setTicketStatus("Success");
					issueValueQRRequest.setTicketValidity("");
					issueValueQRRequest.setIssueDate(valueQRTicketEntity.getIssueDate());
					issueValueQRRequest.setIssueTime("");
					issueValueQRResponseList.add(issueValueQRRequest);
					afcsApiResponse.setPayloadObj(issueValueQRResponseList);
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
					afcsApiResponse.setResMessage("Mobile Verification Succesfull");
					
				}
				
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse qrPassTicket(QRPassRequest qrPassRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		
		try {
			
			log.debug("********qrPassTicket method starts*********");
			List<QRPassRequest> qrPassResponseList = null;
			if(qrPassRequest!=null) {
				QRPassEntity qrPassEntity = new QRPassEntity();
				qrPassEntity.setUserId(qrPassRequest.getUserId());
				// String tktbkngdt = singleJourneyRequest.getTktBookingdt();
				Date tktbkngdt = Date.valueOf(qrPassRequest.getTktBookingdt());
				qrPassEntity.setTktBookingdt(tktbkngdt);
				qrPassEntity.setSrcStnId(qrPassRequest.getSrcStnId());
				qrPassEntity.setDestStnId(qrPassRequest.getDestStnId());
				qrPassEntity.setTktNo(qrPassRequest.getTktNo());
				qrPassEntity.setCustIpAddress(qrPassRequest.getCustIpAddress());
				qrPassEntity.setCustImei(qrPassRequest.getCustImei());
				qrPassEntity.setPassType(qrPassRequest.getPassType());
				qrPassEntity.setPassNo(AFCSUtil.getRandomNum(20));
				qrPassEntity.setPayMode(qrPassRequest.getPayMode());
				qrPassEntity.setPaidAmt(qrPassRequest.getPaidAmt());
				qrPassEntity.setPmtId(qrPassRequest.getPmtId());
				qrPassEntity.setStatus("ACTIVE");
				qrPassEntity.setQrTicketHash(AFCSUtil.getAlphaNumericString(60));
				qrPassEntity = journeyTicketDAO.saveQRPass(qrPassEntity);
				MasterDataEntity masterDataEntity = masterDataDAO.getMasterData();
				if(qrPassEntity.getId()!=null) {
				qrPassResponseList = new ArrayList<QRPassRequest>();
				qrPassRequest.setTkt_status("Success");
				List<PassMasterEntity> passMasterEntityList =  masterDataEntity.getPassList();
				for(PassMasterEntity passMasterEntity : passMasterEntityList) {
					if(qrPassEntity.getPassType().equalsIgnoreCase(passMasterEntity.getPassName())) {
						qrPassRequest.setTkt_validity(passMasterEntity.getValidity());
					}
				}
				qrPassRequest.setQrTicketHash(qrPassEntity.getQrTicketHash());
				qrPassRequest.setPassNo(qrPassEntity.getPassNo());
				qrPassResponseList.add(qrPassRequest);
				afcsApiResponse.setPayloadObj(qrPassResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
				afcsApiResponse.setResMessage("Success");
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			afcsApiResponse.setResSatus(AFCSConstants.INTERNAL_SERVER_ERROR);
			afcsApiResponse.setResMessage("Internal Server Error");
			
		}
		return afcsApiResponse;
	}

	
	


	
	
	

}
