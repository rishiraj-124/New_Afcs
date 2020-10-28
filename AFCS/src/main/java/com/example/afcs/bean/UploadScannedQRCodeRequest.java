package com.example.afcs.bean;

public class UploadScannedQRCodeRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382653084790733211L;
	
	private String ticketSrNum;
	
	private String qrCode;
	
	private String entryDateTime;

	public String getTicketSrNum() {
		return ticketSrNum;
	}

	public void setTicketSrNum(String ticketSrNum) {
		this.ticketSrNum = ticketSrNum;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(String entryDateTime) {
		this.entryDateTime = entryDateTime;
	}
	
	

}
