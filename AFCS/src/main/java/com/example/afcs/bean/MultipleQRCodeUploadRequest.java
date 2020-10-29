package com.example.afcs.bean;

import java.util.List;

public class MultipleQRCodeUploadRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -526551311960460111L;
	
	private List<UploadScannedQRCodeRequest> qrTickets ;

	public List<UploadScannedQRCodeRequest> getQrTickets() {
		return qrTickets;
	}

	public void setQrTickets(List<UploadScannedQRCodeRequest> qrTickets) {
		this.qrTickets = qrTickets;
	} 
	
	

}
