package com.example.afcs.service;

import com.example.afcs.bean.AfcsApiResponse;
import com.example.afcs.bean.CustomerRegistrationRequest;
import com.example.afcs.bean.DriverCondMasterRequest;
import com.example.afcs.bean.IssueValueQRRequest;
import com.example.afcs.bean.MasterDataRequest;
import com.example.afcs.bean.MobileVerificationRequest;
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
import com.example.afcs.model.UserEntity;

public interface TicketingService {

	public AfcsApiResponse singleJourneyFare(SingleJourneyRequest singleJourneyRequest);

	public AfcsApiResponse custReg(CustomerRegistrationRequest customerRegistrationRequest);

	public AfcsApiResponse getMasterData(MasterDataRequest masterDataRequest);

	public AfcsApiResponse scanQRCode(ScanQRTicketRequest scanQRTicketRequest);

	public AfcsApiResponse issueValueQR(IssueValueQRRequest issueValueQRRequest);

	public AfcsApiResponse qrPassTicket(QRPassRequest qrPassRequest);

	public AfcsApiResponse getMyTrips(MyTripsRequest myTripsRequest);

	public UserEntity getUserByToken(String token);

	public AfcsApiResponse getFare(TicketFareRequest ticketFareRequest);

	public AfcsApiResponse getTktSerialNo(SubmitTicketRequest submitTicketRequest);

	public AfcsApiResponse getShiftSumm(ShiftRepoSummRequest shiftRepoSummRequest);

	public AfcsApiResponse getShiftDetail(ShiftRepoSummRequest shiftRepoSummRequest);

	public AfcsApiResponse getMyValidTicket(MyTicketRequest myTicketRequest);

	public AfcsApiResponse getPassDetails(PassFareRequest passFareRequest);

	public AfcsApiResponse getPassSerialNumber(PassSerialNumRequest passSerialNumRequest);

	public AfcsApiResponse getPassQRCode(PassQRRequest passQRRequest);

	public AfcsApiResponse getDriverConductorMasterData(DriverCondMasterRequest driverCondMasterRequest);

	public AfcsApiResponse getValidatedTicketData(TicketDataValidationRequest ticketDataValidationRequest);
	
	public AfcsApiResponse uploadQRCode(UploadScannedQRCodeRequest uploadScannedQRCodeRequest);
	
}
