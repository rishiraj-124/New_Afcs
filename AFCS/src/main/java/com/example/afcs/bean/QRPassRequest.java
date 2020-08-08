package com.example.afcs.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class QRPassRequest implements ValidationBean {

	@NotNull(message="Provide userId property.")
	@NotBlank(message="userId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	private String tktBookingdt;
	
	private Integer srcStnId;
	
	private Integer destStnId;
	
	private Long tktNo;
	
	private String custIpAddress;
	
	private String custImei;
	
	private String passType;
	
	private String passNo;
	
	private String errorMsg;

	private String payMode;
	
	private String paidAmt;
	
	private String pmtId;
	
	private String qrTicketHash;
	
	private String tkt_status;
	
	private String tkt_validity;

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

	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}
	
	public String getPassNo() {
		return passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	@Override
	public String toString() {
		return "QRPassRequest [userId=" + userId + ", tktBookingdt=" + tktBookingdt + ", srcStnId=" + srcStnId
				+ ", destStnId=" + destStnId + ", tktNo=" + tktNo + ", custIpAddress=" + custIpAddress + ", custImei="
				+ custImei + ", passType=" + passType + ", passNo=" + passNo + ", errorMsg=" + errorMsg + ", payMode="
				+ payMode + ", paidAmt=" + paidAmt + ", pmtId=" + pmtId + ", qrTicketHash=" + qrTicketHash
				+ ", tkt_status=" + tkt_status + ", tkt_validity=" + tkt_validity + "]";
	}

	
	
	
	
	
}
