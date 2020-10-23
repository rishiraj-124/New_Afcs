package com.example.afcs.bean;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.example.afcs.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class LoginRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1296724902823740845L;
	
	private String id;
	
	private String token;
	
	/*
	 * @NotNull(message="Provide userId property.")
	 * 
	 * @NotBlank(message="userId cannot be blank")
	 * 
	 * @JsonProperty(value = "userId", required=true)
	 */
	private String userId;
	
	@NotNull(message="Provide mobile property.")
	@NotBlank(message="mobile cannot be blank")
	@JsonProperty(value = "mobile", required=true)
	private String mobile;
	
	@NotNull(message="Provide password property.")
	@NotBlank(message="password cannot be blank")
	@JsonProperty(value = "pwd", required=true)
	private String pwd;
	
	@NotNull(message="Provide imei property.")
	@NotBlank(message="imei cannot be blank")
	@JsonProperty(value = "imei", required=true)
	private String imei;
		
	private Map<String ,Integer> stations;
	
	private String status;
	
	private List<String> userRole;
	
	private String deviceId;
	
	private String apiKey;
	
	private String errorMsg;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Map<String, Integer> getStations() {
		return stations;
	}

	public void setStations(Map<String, Integer> stations) {
		this.stations = stations;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public List<String> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<String> userRole) {
		this.userRole = userRole;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "LoginRequest [id=" + id + ", token=" + token + ", userId=" + userId + ", mobile=" + mobile + ", pwd="
				+ pwd + ", imei=" + imei + ", stations=" + stations + ", status=" + status + ", userRole=" + userRole
				+ ", deviceId=" + deviceId + ", apiKey=" + apiKey + ", errorMsg=" + errorMsg + ", firstName="
				+ firstName + ", lastName=" + lastName + ", emailId=" + emailId + "]";
	}

	



	

	

	


}
