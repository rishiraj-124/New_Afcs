package com.example.afcs.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ChangePasswordRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5017355865528046102L;
	
	@NotNull(message="Provide userId property.")
	@NotBlank(message="userId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	
	@NotNull(message="Provide oldPassword property.")
	@NotBlank(message="oldPassword cannot be blank")
	@JsonProperty(value = "oldPassword", required=true)
	private String oldPassword;
	
	
	@NotNull(message="Provide newPassword property.")
	@NotBlank(message="newPassword cannot be blank")
	@JsonProperty(value = "newPassword", required=true)
	private String newPassword;
	
	private String imei;
	
	private String ipAddress;
	
	private String errorMsg;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
		return "ChangePasswordRequest [userId=" + userId + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", imei=" + imei + ", ipAddress=" + ipAddress + ", errorMsg=" + errorMsg + "]";
	}

	
	
	

}
