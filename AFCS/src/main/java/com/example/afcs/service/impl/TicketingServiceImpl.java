package com.example.afcs.service.impl;

import java.security.SecureRandom;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

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
import com.example.afcs.bean.MyTicketRequest;
import com.example.afcs.bean.MyTripsRequest;
import com.example.afcs.bean.PassFareRequest;
import com.example.afcs.bean.PassQRRequest;
import com.example.afcs.bean.PassSerialNumRequest;
import com.example.afcs.bean.Passenger;
import com.example.afcs.bean.QRPassRequest;
import com.example.afcs.bean.ScanQRTicketRequest;
import com.example.afcs.bean.ShiftRepoSummRequest;
import com.example.afcs.bean.SingleJourneyRequest;
import com.example.afcs.bean.SubmitTicketRequest;
import com.example.afcs.bean.TicketFareRequest;
import com.example.afcs.bean.TransactionDetail;
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
		UserEntity userEntity=null;
		List<CustomerRegistrationRequest> customerRegistrationResponseList = null;
		try {
			log.debug("********custReg method starts*********");
			userEntity = userEntityDAO.getUserProfile(custRegReq.getEmail());
			
			if(userEntity!=null) {
				customerRegistrationResponseList = new ArrayList<CustomerRegistrationRequest>();
				afcsApiResponse.setPayloadObj(customerRegistrationResponseList);
				afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
				afcsApiResponse.setResMessage("Email_Id already Exist");
				return afcsApiResponse;
			}else {
			userEntity = userEntityDAO.findByMobile(custRegReq.getMobile());
				if(userEntity!=null) {
					customerRegistrationResponseList = new ArrayList<CustomerRegistrationRequest>();
					afcsApiResponse.setPayloadObj(customerRegistrationResponseList);
					afcsApiResponse.setResSatus(AFCSConstants.REQUEST_FAILED);
					afcsApiResponse.setResMessage("Mobile already Exist");
					return afcsApiResponse;
				}
			}
			
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

	@Override
	public AfcsApiResponse getMyTrips(MyTripsRequest myTripsRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<MyTripsRequest> myTripsRequestList = new ArrayList<MyTripsRequest>();
		MyTripsRequest myTripsRequest2=null;
		/* static Data Start */
		for(int i=1; i<=Integer.parseInt(myTripsRequest.getNoOfTrips());i++) {
			myTripsRequest2 = new MyTripsRequest();
			
			myTripsRequest2.setCustomerId(myTripsRequest.getCustomerId());
			myTripsRequest2.setDeviceId(myTripsRequest.getDeviceId());
			myTripsRequest2.setDeviceIp(myTripsRequest.getDeviceIp());
			myTripsRequest2.setNoOfTrips(myTripsRequest.getNoOfTrips());
			myTripsRequest2.setTripStartFrom(myTripsRequest.getTripStartFrom());
			
			
			myTripsRequest2.setTripId(String.valueOf(i));
			myTripsRequest2.setTripDate("2020-10-14");
			myTripsRequest2.setTripStartTime("09:15 A.M");
			myTripsRequest2.setTripEndTime("11:19 A.M");
			myTripsRequest2.setTripAmount("60");
			if(i<5) {
			myTripsRequest2.setPaymentMode("Cash");
			}else {
				myTripsRequest2.setPaymentMode("NetBanking");	
			}
			myTripsRequest2.setInStnName("Janakpuri");
			myTripsRequest2.setOutStnName("Dwarka");
			myTripsRequest2.setInGateId("1");
			myTripsRequest2.setOutGateId("5");
		
			myTripsRequestList.add(myTripsRequest2);
		}
		 
		afcsApiResponse.setPayloadObj(myTripsRequestList);
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		afcsApiResponse.setResMessage("Success");
		
		
		/* static Data End */
		return afcsApiResponse;
	}
	
	

	@Override
	public AfcsApiResponse getFare(TicketFareRequest ticketFareRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<TicketFareRequest> ticketFareRequestList = new ArrayList<TicketFareRequest>();
		
		//For Fare***************
		int fare=0; int discount=0;
		int distance = Integer.parseInt(ticketFareRequest.getDesStnId())+Integer.parseInt(ticketFareRequest.getSrcStnId());
		if("1".equalsIgnoreCase(ticketFareRequest.getPaxType())) {
			fare = distance*10;
		}else if("2".equalsIgnoreCase(ticketFareRequest.getPaxType())){
			fare = distance*8;
		} else {
			fare = distance*9;
		}
		
		if("3".equalsIgnoreCase(ticketFareRequest.getPaxType())) {
			discount=5;
		}
		
		
		
		if("1".equalsIgnoreCase(ticketFareRequest.getTktJrnyType())) {
			fare = fare*Integer.parseInt(ticketFareRequest.getNoOfPax());
		}else {
			fare = fare*Integer.parseInt(ticketFareRequest.getNoOfPax());
			fare = fare*2;
		}
		
		int netAmount = fare-discount;
		
		ticketFareRequest.setFareAmt(String.valueOf(fare));
		ticketFareRequest.setDiscount(String.valueOf(discount));
		ticketFareRequest.setNetAmount(String.valueOf(netAmount));
		ticketFareRequestList.add(ticketFareRequest);
		
		
		afcsApiResponse.setPayloadObj(ticketFareRequestList);
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		afcsApiResponse.setResMessage("Success");
		
		return afcsApiResponse;
	}
	
	@Override  
	public UserEntity getUserByToken(String token) {
		UserEntity userEntity=null;
		try {
			
			userEntity = userEntityDAO.getUserByToken(token);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return userEntity;
	}

	@Override
	public AfcsApiResponse getTktSerialNo(SubmitTicketRequest submitTicketRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<SubmitTicketRequest> submitTicketRequestList = new ArrayList<SubmitTicketRequest>();
		
		submitTicketRequest.setTktId(AFCSUtil.getRandomNum(3));
		submitTicketRequest.setTktSrNo(AFCSUtil.getRandomNum(18));
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String sdate = date.format(formatter);
		submitTicketRequest.setTktRequestDate(sdate);
		
		
		Calendar cal = Calendar.getInstance();
		String sTime = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
		
		submitTicketRequest.setTktRequestTime(sTime);
		
		submitTicketRequestList.add(submitTicketRequest);
		
		
		afcsApiResponse.setPayloadObj(submitTicketRequestList);
		afcsApiResponse.setResMessage("Ticket Serial Number generated");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		
		
		
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse getShiftSumm(ShiftRepoSummRequest shiftRepoSummRequest) {
		
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<ShiftRepoSummRequest> submitTicketRequestList = new ArrayList<ShiftRepoSummRequest>();
		
		shiftRepoSummRequest.setCash(AFCSUtil.getRandomNum(4));
		shiftRepoSummRequest.setCard(AFCSUtil.getRandomNum(6));
		
		shiftRepoSummRequest.setTotalAmt(shiftRepoSummRequest.getCash()+shiftRepoSummRequest.getCash());
		submitTicketRequestList.add(shiftRepoSummRequest);
		
		afcsApiResponse.setPayloadObj(submitTicketRequestList);
		afcsApiResponse.setResMessage("Shift Report");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse getShiftDetail(ShiftRepoSummRequest shiftRepoSummRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<ShiftRepoSummRequest> submitTicketRequestList = new ArrayList<ShiftRepoSummRequest>();
		List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();
		TransactionDetail transactionDetail=null;
		if(shiftRepoSummRequest.getTxnNum()!=null && !"".equalsIgnoreCase(shiftRepoSummRequest.getTxnNum())) {
			for (int i = 1; i <= Integer.parseInt(shiftRepoSummRequest.getTxnNum()); i++) {
				transactionDetail = new TransactionDetail();
				transactionDetail.setTxnId(AFCSUtil.getRandomNum(2));
				transactionDetail.setTxnAmnt(AFCSUtil.getRandomNum(3));
				if (i <= 3) {
					transactionDetail.setPaymentMode("Cash");
					transactionDetail.setTxnTime("06:00");
					transactionDetail.setTxnType("Tkt");
				} else {
					transactionDetail.setPaymentMode("Card");
					transactionDetail.setTxnTime("11:50");
					transactionDetail.setTxnType("Pass");
				}
				
				transactionDetailList.add(transactionDetail);
			}
			
			afcsApiResponse.setPayloadObj(transactionDetailList);
			afcsApiResponse.setResMessage("Shift Report");
			afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
			
			
		}else {
			afcsApiResponse.setResMessage("Txn Num can't be empty/null");
		}
		
		
		return afcsApiResponse;
	}

	
	public AfcsApiResponse getMyValidTicket(MyTicketRequest myTicketRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<SingleJourneyRequest> submitTicketRequestList = new ArrayList<SingleJourneyRequest>();

		if ("sjt".equalsIgnoreCase(myTicketRequest.getTicketType())) {
			List<SingleJourneyRequest> singleJourneyRequestList = new ArrayList<SingleJourneyRequest>();
			for (int i = 1; i <= Integer.parseInt(myTicketRequest.getValidTicket()); i++) {
				SingleJourneyRequest singleJourneyRequest = new SingleJourneyRequest();
				singleJourneyRequest.setTktType("SJT");
				if (i > 1) {
					singleJourneyRequest.setPaidAmt("200");
				} else {
					singleJourneyRequest.setPaidAmt("300");
				}
				singleJourneyRequest.setPayMode("Cash");
				singleJourneyRequest.setCustImei(myTicketRequest.getDeviceId());
				singleJourneyRequest.setCustIpAddress(myTicketRequest.getDeviceIp());
				if (i > 1) {
					singleJourneyRequest.setTicketId("325");
				} else {
					singleJourneyRequest.setTicketId("056");
				}

				singleJourneyRequest.setTktNo(Long.valueOf(AFCSUtil.getRandomNum(6)));
				singleJourneyRequest.setQrTicketHash(AFCSUtil.getAlphaNumericString(60));
				singleJourneyRequest.setPaymentStatus("Success");
				singleJourneyRequest.setTkt_status("Success");
				singleJourneyRequest.setTkt_validity("Valid");

				LocalDate date = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String sdate = date.format(formatter);

				singleJourneyRequest.setTktBookingdt(sdate);

				Calendar cal = Calendar.getInstance();
				String sTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
						+ cal.get(Calendar.SECOND);

				singleJourneyRequest.setTktBookingTime(sTime);

				singleJourneyRequest.setTktValiddt(sdate);
				singleJourneyRequest.setTktValidTime(sTime);
				
				submitTicketRequestList.add(singleJourneyRequest);
			}
		}else if("pass".equalsIgnoreCase(myTicketRequest.getTicketType())){
			

			List<SingleJourneyRequest> singleJourneyRequestList = new ArrayList<SingleJourneyRequest>();
			for (int i = 1; i <= Integer.parseInt(myTicketRequest.getValidTicket()); i++) {
				SingleJourneyRequest singleJourneyRequest = new SingleJourneyRequest();
				singleJourneyRequest.setTktType("PASS");
				if (i > 1) {
					singleJourneyRequest.setPaidAmt("200");
				} else {
					singleJourneyRequest.setPaidAmt("300");
				}
				singleJourneyRequest.setPayMode("Cash");
				singleJourneyRequest.setCustImei(myTicketRequest.getDeviceId());
				singleJourneyRequest.setCustIpAddress(myTicketRequest.getDeviceIp());
				if (i > 1) {
					singleJourneyRequest.setTicketId("325");
				} else {
					singleJourneyRequest.setTicketId("056");
				}

				singleJourneyRequest.setTktNo(Long.valueOf(AFCSUtil.getRandomNum(6)));
				singleJourneyRequest.setQrTicketHash(AFCSUtil.getAlphaNumericString(60));
				singleJourneyRequest.setPaymentStatus("Success");
				singleJourneyRequest.setTkt_status("Success");
				singleJourneyRequest.setTkt_validity("Valid");

				LocalDate date = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String sdate = date.format(formatter);

				singleJourneyRequest.setTktBookingdt(sdate);

				Calendar cal = Calendar.getInstance();
				String sTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
						+ cal.get(Calendar.SECOND);

				singleJourneyRequest.setTktBookingTime(sTime);

				singleJourneyRequest.setTktValiddt(sdate);
				singleJourneyRequest.setTktValidTime(sTime);
				
				submitTicketRequestList.add(singleJourneyRequest);
			}
		
		}
		
		afcsApiResponse.setPayloadObj(submitTicketRequestList);
		afcsApiResponse.setResMessage("My Valid Tickets");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse getPassDetails(PassFareRequest passFareRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		
		MasterDataEntity masterDataEntity = masterDataDAO.getMasterData();
		List<PassMasterEntity> passMasterEntities = masterDataEntity.getPassList();
		
		for(PassMasterEntity passMasterEntity :passMasterEntities) {
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String sdate = date.format(formatter);
			passMasterEntity.setDt_Issue(sdate);
			passMasterEntity.setValidFrom(passFareRequest.getSrcStationId());
			passMasterEntity.setValidTo(passFareRequest.getDestStationId());
			passMasterEntity.setPassType(passFareRequest.getPassType());
			passMasterEntity.setPassName(passFareRequest.getPassType());
		}
		
		afcsApiResponse.setPayloadObj(passMasterEntities);
		afcsApiResponse.setResMessage("Trip Pass Details");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse getPassSerialNumber(PassSerialNumRequest passSerialNumRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<PassSerialNumRequest> passSerialNumRequestList = new ArrayList<PassSerialNumRequest>();
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String sdate = date.format(formatter);
		
		passSerialNumRequest.setDtIssue(sdate);
		

		Calendar cal = Calendar.getInstance();
		String sTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
				+ cal.get(Calendar.SECOND);
		
		passSerialNumRequest.setValidUpto(sdate);
		passSerialNumRequest.setPassId(AFCSUtil.getRandomNum(2));
		passSerialNumRequest.setPassSerialNum(AFCSUtil.getRandomNum(8));
		passSerialNumRequest.setPassRequestDate(sdate);
		passSerialNumRequest.setPassRequestTime(sTime);
		passSerialNumRequest.setDuration("30 Days");
		
		passSerialNumRequestList.add(passSerialNumRequest);
		
		afcsApiResponse.setPayloadObj(passSerialNumRequestList);
		afcsApiResponse.setResMessage("Pass Ticket Serial Num generated");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		return afcsApiResponse;
	}

	@Override
	public AfcsApiResponse getPassQRCode(PassQRRequest passQRRequest) {
		AfcsApiResponse afcsApiResponse = new AfcsApiResponse();
		List<PassQRRequest> passQRRequestList = new ArrayList<PassQRRequest>();
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String sdate = date.format(formatter);
		
		passQRRequest.setDtIssue(sdate);
		
		Calendar cal = Calendar.getInstance();
		String sTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":"
				+ cal.get(Calendar.SECOND);
		passQRRequest.setPassRequestTime(sTime);
		
		passQRRequest.setValidUpto(sdate);
		passQRRequest.setValidUptoTime(sTime);
		
		passQRRequest.setPassQRCode(AFCSUtil.getAlphaNumericString(60));
		passQRRequest.setDuration("30 Days");
		passQRRequest.setDocType("Aadhaar");
		passQRRequest.setDocRequired("Yes");
		passQRRequest.setDocAttached(AFCSUtil.getAlphaNumericString(20));
		
		passQRRequestList.add(passQRRequest);
		
		afcsApiResponse.setPayloadObj(passQRRequestList);
		afcsApiResponse.setResMessage("Pass QRCode generated");
		afcsApiResponse.setResSatus(AFCSConstants.REQUEST_PROCESSED_SUCCESSFULLY);
		
		return afcsApiResponse;
	}

	

}
