package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class Passes {

	private int passId;
	
	private String passPeriod;
	
	private String passName;
	
	private String tripAllowed;
	
	private Long passAmt;
	
	private String validity;
	
	private String passType;
	
	private String distance_src_dest;
	
	private String docReq;

	public int getPassId() {
		return passId;
	}

	public void setPassId(int passId) {
		this.passId = passId;
	}

	public String getPassPeriod() {
		return passPeriod;
	}

	public void setPassPeriod(String passPeriod) {
		this.passPeriod = passPeriod;
	}

	public String getPassName() {
		return passName;
	}

	public void setPassName(String passName) {
		this.passName = passName;
	}

	public String getTripAllowed() {
		return tripAllowed;
	}

	public void setTripAllowed(String tripAllowed) {
		this.tripAllowed = tripAllowed;
	}

	public Long getPassAmt() {
		return passAmt;
	}

	public void setPassAmt(Long passAmt) {
		this.passAmt = passAmt;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}

	public String getDistance_src_dest() {
		return distance_src_dest;
	}

	public void setDistance_src_dest(String distance_src_dest) {
		this.distance_src_dest = distance_src_dest;
	}

	public String getDocReq() {
		return docReq;
	}

	public void setDocReq(String docReq) {
		this.docReq = docReq;
	}
	
	
}
