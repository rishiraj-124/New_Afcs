package com.example.afcs.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ScanQRTicketRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1182345207541077107L;
	
	@NotNull(message="Provide tktQRCode property.")
	@NotBlank(message="tktQRCode cannot be blank")
	@JsonProperty(value = "tktQRCode", required=true)
	private String tktQRCode;
	
	@NotNull(message="Provide deviceId property.")
	@NotBlank(message="deviceId cannot be blank")
	@JsonProperty(value = "deviceId", required=true)
	private String deviceId;
	
	@NotNull(message="Provide ipAddress property.")
	@NotBlank(message="ipAddress cannot be blank")
	@JsonProperty(value = "ipAddress", required=true)
	private String ipAddress;
	
	private String errorMsg;

	public String getTktQRCode() {
		return tktQRCode;
	}

	public void setTktQRCode(String tktQRCode) {
		this.tktQRCode = tktQRCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	
	

}
