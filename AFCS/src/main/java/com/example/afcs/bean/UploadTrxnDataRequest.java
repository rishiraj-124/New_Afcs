package com.example.afcs.bean;

import java.util.List;

public class UploadTrxnDataRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925414860254423108L;

	private String userId;
	private String readerId;
	private String busNo;
	private String trip;
	private String routeNo;
	private String busServiceType;
	private List<TransactionTicket> txnTicketData;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getTrip() {
		return trip;
	}
	public void setTrip(String trip) {
		this.trip = trip;
	}
	public String getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
	public String getBusServiceType() {
		return busServiceType;
	}
	public void setBusServiceType(String busServiceType) {
		this.busServiceType = busServiceType;
	}
	public List<TransactionTicket> getTxnTicketData() {
		return txnTicketData;
	}
	public void setTxnTicketData(List<TransactionTicket> txnTicketData) {
		this.txnTicketData = txnTicketData;
	}
	
	
	
}
