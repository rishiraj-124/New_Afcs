package com.example.afcs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "AfcsGateDeviceMaster")
@NamedQueries
({
	@NamedQuery(name="AfcsGateDeviceMaster.findByDeviceId", query="SELECT afgt FROM AfcsGateDeviceMaster afgt WHERE afgt.deviceId = :deviceId")
	
})
public class AfcsGateDeviceMaster implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "deviceId")
	private String deviceId;
	
	@Column(name = "ipAddress")
	private String ipAddress;
	
	@Column(name = "atStation")
	private String atStation;
	
	@Column(name = "gateType")
	private String gateType;
	
	@Column(name = "status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAtStation() {
		return atStation;
	}

	public void setAtStation(String atStation) {
		this.atStation = atStation;
	}

	public String getGateType() {
		return gateType;
	}

	public void setGateType(String gateType) {
		this.gateType = gateType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
