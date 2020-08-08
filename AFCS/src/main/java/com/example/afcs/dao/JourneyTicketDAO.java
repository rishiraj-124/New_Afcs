package com.example.afcs.dao;

import com.example.afcs.model.AfcsGateDeviceMaster;
import com.example.afcs.model.QRPassEntity;
import com.example.afcs.model.SingleJourneyEntitiy;
import com.example.afcs.model.UserEntity;
import com.example.afcs.model.ValueQRTicketEntity;

public interface JourneyTicketDAO {

	public SingleJourneyEntitiy saveJourney(SingleJourneyEntitiy singleJourneyEntitiy);
	
	public SingleJourneyEntitiy updateJourney(SingleJourneyEntitiy singleJourneyEntitiy);
	
	public SingleJourneyEntitiy getSingleJourney(String qrCode);
	
	public AfcsGateDeviceMaster getGateDeviceId(String deviceId);
	
	public ValueQRTicketEntity saveValueQR(ValueQRTicketEntity valueQRTicketEntity);
	
	public QRPassEntity saveQRPass(QRPassEntity qrPassEntity);
	
}
