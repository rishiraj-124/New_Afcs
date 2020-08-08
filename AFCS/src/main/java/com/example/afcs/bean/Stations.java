package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class Stations {

	
	private int stnId;
	
	private String stnName;
	
	private boolean isInterChange;
	
	private String interChangeLine;
	
	private String orgLine;

	public int getStnId() {
		return stnId;
	}

	public void setStnId(int stnId) {
		this.stnId = stnId;
	}

	public String getStnName() {
		return stnName;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}

	public boolean isInterChange() {
		return isInterChange;
	}

	public void setInterChange(boolean isInterChange) {
		this.isInterChange = isInterChange;
	}

	public String getInterChangeLine() {
		return interChangeLine;
	}

	public void setInterChangeLine(String interChangeLine) {
		this.interChangeLine = interChangeLine;
	}

	public String getOrgLine() {
		return orgLine;
	}

	public void setOrgLine(String orgLine) {
		this.orgLine = orgLine;
	}
	
	
}
