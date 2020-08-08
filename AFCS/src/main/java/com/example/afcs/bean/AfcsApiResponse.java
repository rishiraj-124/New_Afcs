package com.example.afcs.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "status", "message", "payload"})
public class AfcsApiResponse implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467233319839672148L;

	@JsonProperty("status")
	private Integer resSatus;
	
	@JsonProperty("message")
	private String resMessage;
	
	
	@JsonProperty("payload")
	private transient Object payloadObj;


	public Integer getResSatus() {
		return resSatus;
	}


	public void setResSatus(Integer resSatus) {
		this.resSatus = resSatus;
	}


	public String getResMessage() {
		return resMessage;
	}


	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}


	public Object getPayloadObj() {
		return payloadObj;
	}


	public void setPayloadObj(Object payloadObj) {
		this.payloadObj = payloadObj;
	}


	@Override
	public String toString() {
		return "AfcsApiResponse [resSatus=" + resSatus + ", resMessage=" + resMessage + ", payloadObj=" + payloadObj
				+ "]";
	}
	
	
	
}
