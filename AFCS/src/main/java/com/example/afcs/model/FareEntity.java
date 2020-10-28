package com.example.afcs.model;

public class FareEntity {
	
	private String fareType;
	private String minDistance;
	private String maxDistance;
	private String fareAmnt;
	private String busServiceType;
	
	public String getFareType() {
		return fareType;
	}
	public void setFareType(String fareType) {
		this.fareType = fareType;
	}
	public String getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(String minDistance) {
		this.minDistance = minDistance;
	}
	public String getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(String maxDistance) {
		this.maxDistance = maxDistance;
	}
	public String getFareAmnt() {
		return fareAmnt;
	}
	public void setFareAmnt(String fareAmnt) {
		this.fareAmnt = fareAmnt;
	}
	public String getBusServiceType() {
		return busServiceType;
	}
	public void setBusServiceType(String busServiceType) {
		this.busServiceType = busServiceType;
	}
	
	

}
