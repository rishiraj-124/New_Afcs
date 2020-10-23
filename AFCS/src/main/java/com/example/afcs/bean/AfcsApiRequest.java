package com.example.afcs.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class AfcsApiRequest  implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889621243991005826L;
	
	
	@NotNull(message="Provide channelId property.")
	@NotBlank(message="channelId cannot be blank")		
	@JsonProperty(value = "channelId", required=true)
	private String channelId;

	@NotNull(message="Provide tokenId property.")
	@NotBlank(message="tokenId cannot be blank")
	@JsonProperty(value = "tokenId", required=true)
	private String tokenId;

	
	 
	@NotNull(message="Provide payload property.")
	@NotBlank(message="payload cannot be blank")
	@JsonProperty(value = "payload" , required=true) 
    private Object payloadObj;


	public String getChannelId() {
		return channelId;
	}


	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	public String getTokenId() {
		return tokenId;
	}


	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}


	public Object getPayloadObj() {
		return payloadObj;
	}


	public void setPayloadObj(Object payloadObj) {
		this.payloadObj = payloadObj;
	}


	@Override
	public String toString() {
		return "AfcsApiRequest [channelId=" + channelId + ", tokenId=" + tokenId + ", payloadObj=" + payloadObj + "]";
	}
    
    
}

