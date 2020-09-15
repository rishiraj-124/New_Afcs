package com.example.afcs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GateArray")
public class GateArray implements Serializable {

	private static final long serialVersionUID = 1132829114676176532L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "gateType")
	private String gateType;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "quantity")
	private Long quantity;
	
	
	@ManyToOne
	@JoinColumn(name="SMID", nullable=false) 
	private StationMasterEntity stationMaster;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getGateType() {
		return gateType;
	}


	public void setGateType(String gateType) {
		this.gateType = gateType;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public StationMasterEntity getStationMaster() {
		return stationMaster;
	}


	public void setStationMaster(StationMasterEntity stationMaster) {
		this.stationMaster = stationMaster;
	}
	
	
}
