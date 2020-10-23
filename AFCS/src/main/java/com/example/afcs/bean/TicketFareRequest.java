package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class TicketFareRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8043262647516031113L;
	
	private String ticketType;
	
	private String srcStnId;
	
	private String desStnId;
	
	private String paxType;
	
	private String tktJrnyType;
	
	private String noOfPax;
	
	private String fareAmt;
	
	private String discount;
	
	private String netAmount;

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getSrcStnId() {
		return srcStnId;
	}

	public void setSrcStnId(String srcStnId) {
		this.srcStnId = srcStnId;
	}

	public String getDesStnId() {
		return desStnId;
	}

	public void setDesStnId(String desStnId) {
		this.desStnId = desStnId;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public String getTktJrnyType() {
		return tktJrnyType;
	}

	public void setTktJrnyType(String tktJrnyType) {
		this.tktJrnyType = tktJrnyType;
	}

	public String getNoOfPax() {
		return noOfPax;
	}

	public void setNoOfPax(String noOfPax) {
		this.noOfPax = noOfPax;
	}

	public String getFareAmt() {
		return fareAmt;
	}

	public void setFareAmt(String fareAmt) {
		this.fareAmt = fareAmt;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	@Override
	public String toString() {
		return "TicketFareRequest [ticketType=" + ticketType + ", srcStnId=" + srcStnId + ", desStnId=" + desStnId
				+ ", paxType=" + paxType + ", tktJrnyType=" + tktJrnyType + ", noOfPax=" + noOfPax + ", fareAmt="
				+ fareAmt + ", discount=" + discount + ", netAmount=" + netAmount + "]";
	}
	
	

}
