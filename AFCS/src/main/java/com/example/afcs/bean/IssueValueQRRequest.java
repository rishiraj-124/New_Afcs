package com.example.afcs.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class IssueValueQRRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8943387538279334392L;
	
	private Long amount;
	
	private String paymentMode;
	
	private String imei;
	
	
	private String ipAddress;
	private String ticketId;
	
	private String fullTicketNo;
	
	private String qrTicketHash;
	
	private String paymentStatus;
	
	private String ticketStatus;
	
	private String ticketValidity;
	
	private String issueDate;
	
	private String issueTime;
	
	private String errorMsg;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getFullTicketNo() {
		return fullTicketNo;
	}

	public void setFullTicketNo(String fullTicketNo) {
		this.fullTicketNo = fullTicketNo;
	}

	public String getQrTicketHash() {
		return qrTicketHash;
	}

	public void setQrTicketHash(String qrTicketHash) {
		this.qrTicketHash = qrTicketHash;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getTicketValidity() {
		return ticketValidity;
	}

	public void setTicketValidity(String ticketValidity) {
		this.ticketValidity = ticketValidity;
	}


	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "IssueValueQRRequest [amount=" + amount + ", paymentMode=" + paymentMode + ", imei=" + imei
				+ ", ipAddress=" + ipAddress + ", ticketId=" + ticketId + ", fullTicketNo=" + fullTicketNo
				+ ", qrTicketHash=" + qrTicketHash + ", paymentStatus=" + paymentStatus + ", ticketStatus="
				+ ticketStatus + ", ticketValidity=" + ticketValidity + ", issueDate=" + issueDate + ", issueTime="
				+ issueTime + ", errorMsg=" + errorMsg + "]";
	}

	
	

}
