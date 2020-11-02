package com.example.afcs.bean;

public class ForgetPasswordRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7875516385882309106L;
	
	private String userId;
	
	private String imei;
	
	private String ipAddress;
	
	private String otp;
	
	private String newPassword;

	private String mobile;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ForgetPasswordRequest [userId=" + userId + ", imei=" + imei + ", ipAddress=" + ipAddress + ", otp="
				+ otp + ", newPassword=" + newPassword + ", mobile=" + mobile + "]";
	}

	
	
	

}
