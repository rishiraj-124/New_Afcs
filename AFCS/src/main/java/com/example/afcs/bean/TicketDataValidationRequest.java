package com.example.afcs.bean;

public class TicketDataValidationRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1173993880619753862L;
	
	private String deviceId;
	
	private String routeId;
	
	private String busId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}
	
	

}
