package com.example.afcs.model;

import java.util.List;

import com.example.afcs.bean.Passenger;
import com.example.afcs.bean.Passes;
import com.example.afcs.bean.Stations;


public class MasterDataEntity {

	private List<TicketMasterEntity> ticketList;
	
	private List<PassMasterEntity> passList;
	
	private List<PassengerMasterEntity> pssngrList;
	
	private List<StationMasterEntity> stnList;

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


}
