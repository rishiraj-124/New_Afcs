package com.example.afcs.bean;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class LoginRequest implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1296724902823740845L;
	
	private String id;
	
	private String token;
	
	@NotNull(message="Provide userId property.")
	@NotBlank(message="userId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	@NotNull(message="Provide password property.")
	@NotBlank(message="password cannot be blank")
	@JsonProperty(value = "pwd", required=true)
	private String pwd;
	
	private Map<String ,Integer> stations;
	
	private String status;
	
	private List<String> userRole;
	
	private String deviceId;
	
	private String apiKey;
	
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

	private String errorMsg;

	
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	@Override
	public String toString() {
		return "LoginRequest [id=" + id + ", token=" + token + ", userId=" + userId + ", pwd=" + pwd + ", stations="
				+ stations + ", status=" + status + ", userRole=" + userRole + ", errorMsg=" + errorMsg + "]";
	}

	

	

	


}
