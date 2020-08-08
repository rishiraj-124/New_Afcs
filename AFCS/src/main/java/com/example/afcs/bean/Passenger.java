package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class Passenger {

	private String pssTypeId;
	
	private String pssType;
	
	private Integer noOfTkt;
	
	private Long tktAmount;
	
	private Integer discount;
	
	private Long amtPaid;
	
	private Long totalAmt;
	
	public String getPssTypeId() {
		return pssTypeId;
	}

	public void setPssTypeId(String pssTypeId) {
		this.pssTypeId = pssTypeId;
	}
	
		public String getPssType() {
		return pssType;
	}

	public void setPssType(String pssType) {
		this.pssType = pssType;
	}

	public Integer getNoOfTkt() {
		return noOfTkt;
	}

	public void setNoOfTkt(Integer noOfTkt) {
		this.noOfTkt = noOfTkt;
	}

	public Long getTktAmount() {
		return tktAmount;
	}

	public void setTktAmount(Long tktAmount) {
		this.tktAmount = tktAmount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Long getAmtPaid() {
		return amtPaid;
	}

	public void setAmtPaid(Long amtPaid) {
		this.amtPaid = amtPaid;
	}

	public Long getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Long totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Override
	public String toString() {
		return "Passenger [pssType=" + pssType + ", noOfTkt=" + noOfTkt + ", tktAmount=" + tktAmount + ", discount="
				+ discount + ", amtPaid=" + amtPaid + ", totalAmt=" + totalAmt + "]";
	}

	
	
	
}
