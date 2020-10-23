package com.example.afcs.bean;

public class MyTicketRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3452933086066522581L;
	
	private String customerId;
	
	private String deviceId;
	
	private String deviceIp;
	
	private String validTicket;
	
	private String ticketType;

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

	public String getValidTicket() {
		return validTicket;
	}

	public void setValidTicket(String validTicket) {
		this.validTicket = validTicket;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "MyTicketRequest [customerId=" + customerId + ", deviceId=" + deviceId + ", deviceIp=" + deviceIp
				+ ", validTicket=" + validTicket + ", ticketType=" + ticketType + "]";
	}
	
	
}
