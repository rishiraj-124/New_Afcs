package com.example.afcs.model;

import java.util.List;

public class RouteEntity {
	
	private String routeNo;
	private String srcStation;
	private String destStation;
	
	private List<StopEntity> stops;

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getSrcStation() {
		return srcStation;
	}

	public void setSrcStation(String srcStation) {
		this.srcStation = srcStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public List<StopEntity> getStops() {
		return stops;
	}

	public void setStops(List<StopEntity> stops) {
		this.stops = stops;
	}

	
	
	
	
}
