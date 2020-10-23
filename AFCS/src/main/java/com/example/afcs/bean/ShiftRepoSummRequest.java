package com.example.afcs.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ShiftRepoSummRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5351290920041441965L;
	
	
	private String 	agentId;
	
	private String deviceId;
	
	private String deviceIp;
	
	private String stationCode;
	
	private String dtOfShift;
	
	private String timeFrom;
	
	private String timeTo;
	
	
	private String txnNum;
	
	private String txnStartsFrom;
	
	private List<TransactionDetail> transactionDetailList;
	
	private String cash;
	
	private String card;
	
	private String totalAmt;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getDtOfShift() {
		return dtOfShift;
	}

	public void setDtOfShift(String dtOfShift) {
		this.dtOfShift = dtOfShift;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	
	public String getTxnNum() {
		return txnNum;
	}

	public void setTxnNum(String txnNum) {
		this.txnNum = txnNum;
	}

	public String getTxnStartsFrom() {
		return txnStartsFrom;
	}

	public void setTxnStartsFrom(String txnStartsFrom) {
		this.txnStartsFrom = txnStartsFrom;
	}

	public List<TransactionDetail> getTransactionDetailList() {
		return transactionDetailList;
	}

	public void setTransactionDetailList(List<TransactionDetail> transactionDetailList) {
		this.transactionDetailList = transactionDetailList;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Override
	public String toString() {
		return "ShiftRepoSummRequest [agentId=" + agentId + ", deviceId=" + deviceId + ", deviceIp=" + deviceIp
				+ ", stationCode=" + stationCode + ", dtOfShift=" + dtOfShift + ", timeFrom=" + timeFrom + ", timeTo="
				+ timeTo + ", cash=" + cash + ", card=" + card + ", totalAmt=" + totalAmt + "]";
	}
	
	
	
	
}
