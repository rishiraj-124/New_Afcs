package com.example.afcs.bean;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.example.afcs.model.PassMasterEntity;
import com.example.afcs.model.PassengerMasterEntity;
import com.example.afcs.model.StationMasterEntity;
import com.example.afcs.model.TicketMasterEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class MasterDataRequest implements ValidationBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -855977346047918508L;

	@NotNull(message="Provide masterData property.")
	@NotBlank(message="MasterData cannot be blank")
	@JsonProperty(value = "masterData", required=true)
	private String masterData;
	
	@NotNull(message="Provide userId property.")
	@NotBlank(message="UserId cannot be blank")
	@JsonProperty(value = "userId", required=true)
	private String userId;
	
	private List<TicketMasterEntity> ticketList;
	
	private List<PassMasterEntity> passList;
	
	private List<PassengerMasterEntity> pssngrList;
	
	private List<StationMasterEntity> stnList;
	
	private String errorMsg;


	public String getMasterData() {
		return masterData;
	}

	public void setMasterData(String masterData) {
		this.masterData = masterData;
	}

	public List<TicketMasterEntity> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<TicketMasterEntity> ticketList) {
		this.ticketList = ticketList;
	}

	public List<PassMasterEntity> getPassList() {
		return passList;
	}

	public void setPassList(List<PassMasterEntity> passList) {
		this.passList = passList;
	}

	public List<PassengerMasterEntity> getPssngrList() {
		return pssngrList;
	}

	public void setPssngrList(List<PassengerMasterEntity> pssngrList) {
		this.pssngrList = pssngrList;
	}

	public List<StationMasterEntity> getStnList() {
		return stnList;
	}

	public void setStnList(List<StationMasterEntity> stnList) {
		this.stnList = stnList;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
	public String toString() {
		return "MasterDataRequest [masterData=" + masterData + ", userId=" + userId + ", ticketList=" + ticketList
				+ ", passList=" + passList + ", pssngrList=" + pssngrList + ", stnList=" + stnList + ", errorMsg="
				+ errorMsg + "]";
	}


	
	
}
