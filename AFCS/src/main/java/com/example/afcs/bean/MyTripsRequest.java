package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class MyTripsRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2678196544174847022L;
	
	private String customerId;
	
	private String deviceId;
	
	private String deviceIp;
	
	private String noOfTrips;
	
	private String tripStartFrom;
	
	//below field for static data 
	
	private String tripId;
	
	private String tripDate;
	
	private String tripStartTime;
	
	private String tripAmount;
	
	private String paymentMode;
	
	private String tripEndTime;
	
	private String inStnName;
	
	private String outStnName;
	
	private String inGateId;
	
	private String outGateId;
	
	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getNoOfTrips() {
		return noOfTrips;
	}

	public void setNoOfTrips(String noOfTrips) {
		this.noOfTrips = noOfTrips;
	}

	public String getTripStartFrom() {
		return tripStartFrom;
	}

	public void setTripStartFrom(String tripStartFrom) {
		this.tripStartFrom = tripStartFrom;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripStartTime() {
		return tripStartTime;
	}

	public void setTripStartTime(String tripStartTime) {
		this.tripStartTime = tripStartTime;
	}

	public String getTripAmount() {
		return tripAmount;
	}

	public void setTripAmount(String tripAmount) {
		this.tripAmount = tripAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTripEndTime() {
		return tripEndTime;
	}

	public void setTripEndTime(String tripEndTime) {
		this.tripEndTime = tripEndTime;
	}

	public String getInStnName() {
		return inStnName;
	}

	public void setInStnName(String inStnName) {
		this.inStnName = inStnName;
	}

	public String getOutStnName() {
		return outStnName;
	}

	public void setOutStnName(String outStnName) {
		this.outStnName = outStnName;
	}

	public String getInGateId() {
		return inGateId;
	}

	public void setInGateId(String inGateId) {
		this.inGateId = inGateId;
	}

	public String getOutGateId() {
		return outGateId;
	}

	public void setOutGateId(String outGateId) {
		this.outGateId = outGateId;
	}
	
	
	
	

}
