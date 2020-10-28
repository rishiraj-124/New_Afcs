package com.example.afcs.model;

import java.util.List;

public class BusInfoEntity {
	
	private String vehicleNo;
	private String busType;
	private String manufacturer;
	
	private List<RouteEntity> routes;
	private List<FareEntity> fareRules;
	private List<DiscountEntity> discounts;
	private List<BusPassEntity> passAllowed;
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public List<RouteEntity> getRoutes() {
		return routes;
	}
	public void setRoutes(List<RouteEntity> routes) {
		this.routes = routes;
	}
	
	
	public List<FareEntity> getFareRules() {
		return fareRules;
	}
	public void setFareRules(List<FareEntity> fareRules) {
		this.fareRules = fareRules;
	}
	public List<DiscountEntity> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<DiscountEntity> discounts) {
		this.discounts = discounts;
	}
	public List<BusPassEntity> getPassAllowed() {
		return passAllowed;
	}
	public void setPassAllowed(List<BusPassEntity> passAllowed) {
		this.passAllowed = passAllowed;
	}
	
	
	

}
