package com.example.afcs.bean;

public class DriverCondLoginRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8360549426140319135L;
	
	
	private String mobile;
	
	private String password;
	
	private String deviceId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return password;
	}

	public void setPwd(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	

}
