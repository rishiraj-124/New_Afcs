package com.example.afcs.bean;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class SubmitTicketRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 681987109818804647L;
	
	@NotNull(message="Provide userId property.")
	@NotBlank(message="userId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	private Integer srcStnId;
	
	private Integer destStnId;
	
	private String tktSrNo;
	
	private String custIpAddress;
	
	private String custImei;
	
	private String tktType;
	
	private List<Passenger> psgList;
	
	private String tktId;
	
	private String tktRequestDate;
	
	private String tktRequestTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getTktSrNo() {
		return tktSrNo;
	}

	public void setTktSrNo(String tktSrNo) {
		this.tktSrNo = tktSrNo;
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

	public String getTktId() {
		return tktId;
	}

	public void setTktId(String tktId) {
		this.tktId = tktId;
	}

	public String getTktRequestDate() {
		return tktRequestDate;
	}

	public void setTktRequestDate(String tktRequestDate) {
		this.tktRequestDate = tktRequestDate;
	}

	public String getTktRequestTime() {
		return tktRequestTime;
	}

	public void setTktRequestTime(String tktRequestTime) {
		this.tktRequestTime = tktRequestTime;
	}

	@Override
	public String toString() {
		return "SubmitTicketRequest [userId=" + userId + ", srcStnId=" + srcStnId
				+ ", destStnId=" + destStnId + ", tktSrNo=" + tktSrNo + ", custIpAddress=" + custIpAddress
				+ ", custImei=" + custImei + ", tktType=" + tktType + ", psgList=" + psgList + ", tktId=" + tktId
				+ ", tktRequestDate=" + tktRequestDate + ", tktRequestTime=" + tktRequestTime + "]";
	}
	
	
	
	
}
