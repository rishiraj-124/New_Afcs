package com.example.afcs.service;

import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.CustomerRegistrationRequest;
import com.example.afcs.bean.IssueValueQRRequest;
import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.bean.MobileVerificationRequest;
import com.example.afcs.bean.QRPassRequest;
import com.example.afcs.bean.ScanQRTicketRequest;
import com.example.afcs.bean.SingleJourneyRequest;

public interface TicketingService {
	
	
	public AfcsApiResponse singleJourneyFare(SingleJourneyRequest singleJourneyRequest);
	
	public AfcsApiResponse custReg(CustomerRegistrationRequest customerRegistrationRequest);
	
	public AfcsApiResponse getMasterData(MasterDataRequest masterDataRequest);
	
	public AfcsApiResponse scanQRCode(ScanQRTicketRequest scanQRTicketRequest);
	
	public AfcsApiResponse issueValueQR(IssueValueQRRequest issueValueQRRequest);
	
	public AfcsApiResponse qrPassTicket(QRPassRequest qrPassRequest);
	
	
}
