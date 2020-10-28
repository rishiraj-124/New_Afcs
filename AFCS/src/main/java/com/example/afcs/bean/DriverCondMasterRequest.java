package com.example.afcs.bean;

public class DriverCondMasterRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319131631386607022L;
	
	private String mobile;
	private String deviceId;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	

}
