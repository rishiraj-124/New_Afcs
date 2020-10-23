package com.example.afcs.bean;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class SingleJourneyRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6803599659186089433L;

	@NotNull(message="Provide userId property.")
	@NotBlank(message="userId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	private String tktBookingdt;
	
	private String tktBookingTime;
	
	private String tktValiddt;
	
	private String tktValidTime;
	
	private Integer srcStnId;
	
	private Integer destStnId;
	
	private Long tktNo;
	
	private String custIpAddress;
	
	private String custImei;
	
	private String tktType;
	
	private List<Passenger> psgList;
	
	private String errorMsg;

	private String payMode;
	
	private String paidAmt;
	
	private String pmtId;
	
	private String qrTicketHash;
	
	private String tkt_status;
	
	private String tkt_validity;
	
	private String ticketId;
	
	private String paymentStatus;

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTktBookingdt() {
		return tktBookingdt;
	}


	public void setTktBookingdt(String tktBookingdt) {
		this.tktBookingdt = tktBookingdt;
	}


	public Integer getSrcStnId() {
		return srcStnId;
	}


	public void setSrcStnId(Integer srcStnId) {
		this.srcStnId = srcStnId;
	}


	public Integer getDestStnId() {
		return destStnId;
	}


	public void setDestStnId(Integer destStnId) {
		this.destStnId = destStnId;
	}


	public Long getTktNo() {
		return tktNo;
	}


	public void setTktNo(Long tktNo) {
		this.tktNo = tktNo;
	}


	public String getCustIpAddress() {
		return custIpAddress;
	}


	public void setCustIpAddress(String custIpAddress) {
		this.custIpAddress = custIpAddress;
	}


	public String getCustImei() {
		return custImei;
	}


	public void setCustImei(String custImei) {
		this.custImei = custImei;
	}


	public String getTktType() {
		return tktType;
	}


	public void setTktType(String tktType) {
		this.tktType = tktType;
	}


	public List<Passenger> getPsgList() {
		return psgList;
	}


	public void setPsgList(List<Passenger> psgList) {
		this.psgList = psgList;
	}


	public String getPayMode() {
		return payMode;
	}


	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}


	public String getPaidAmt() {
		return paidAmt;
	}


	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}


	public String getPmtId() {
		return pmtId;
	}


	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getQrTicketHash() {
		return qrTicketHash;
	}


	public void setQrTicketHash(String qrTicketHash) {
		this.qrTicketHash = qrTicketHash;
	}

	public String getTkt_status() {
		return tkt_status;
	}


	public void setTkt_status(String tkt_status) {
		this.tkt_status = tkt_status;
	}


	public String getTkt_validity() {
		return tkt_validity;
	}


	public void setTkt_validity(String tkt_validity) {
		this.tkt_validity = tkt_validity;
	}
	
	


	public String getTicketId() {
		return ticketId;
	}


	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	



	public String getTktBookingTime() {
		return tktBookingTime;
	}


	public void setTktBookingTime(String tktBookingTime) {
		this.tktBookingTime = tktBookingTime;
	}


	public String getTktValiddt() {
		return tktValiddt;
	}


	public void setTktValiddt(String tktValiddt) {
		this.tktValiddt = tktValiddt;
	}


	public String getTktValidTime() {
		return tktValidTime;
	}


	public void setTktValidTime(String tktValidTime) {
		this.tktValidTime = tktValidTime;
	}


	@Override
	public String toString() {
		return "SingleJourneyRequest [userId=" + userId + ", tktBookingdt=" + tktBookingdt + ", srcStnId=" + srcStnId
				+ ", destStnId=" + destStnId + ", tktNo=" + tktNo + ", custIpAddress=" + custIpAddress + ", custImei="
				+ custImei + ", tktType=" + tktType + ", psgList=" + psgList + ", errorMsg=" + errorMsg + ", payMode="
				+ payMode + ", paidAmt=" + paidAmt + ", pmtId=" + pmtId + ", qrTicketHash=" + qrTicketHash
				+ ", tkt_status=" + tkt_status + ", tkt_validity=" + tkt_validity + "]";
	}




	
	
}
