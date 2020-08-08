package com.example.afcs.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileVerificationRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4724235630420871926L;
	
	@NotNull(message="Otp cannot be null.")	
	@NotBlank(message="Otp cannot be blank.")
	@JsonProperty(value = "otp")
	private String otp;
	
	private String imei;
	
	private String ipAddress;
	
	private String errorMsg;
	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "MobileVerificationRequest [otp=" + otp + ", imei=" + imei + ", ipAddress=" + ipAddress + ", errorMsg="
				+ errorMsg + "]";
	}

	
	
	
}
