package com.example.afcs.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class AfcsApiRequest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889621243991005826L;
	
	
	//@NotNull(message="Channel ID cannot be null.")		
	@JsonProperty(value = "channelId")
	private Integer channelId;

	//@NotBlank(message="Token ID cannot be blank.")
	@JsonProperty(value = "tokenId")
	private String tokenId;

      
    @JsonProperty(value = "payload", required = false)
    private transient Object payloadObj;


	public Integer getChannelId() {
		return channelId;
	}


	public void setChannelId(Integer channelId) {
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

