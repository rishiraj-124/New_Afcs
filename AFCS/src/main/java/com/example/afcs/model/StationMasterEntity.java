package com.example.afcs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "StationMaster")
public class StationMasterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074958225776489468L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name ="stnId")
	private Integer stnId;
	
	@Column(name ="stnName")
	private String stnName;
	
	@Column(name ="stnAddress")
	private String stnAddress;
	
	@Column(name ="landMark")
	private String landMark;
	
	@Column(name ="gpsLocation")
	private Long gpsLocation;;
	
	@Column(name ="stnBrandName")
	private String stnBrandName;
	
 	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<EmergencyContact> emergencyContactList;
   	
   	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<SecurityContact> securityContactList;
	
	@Column(name ="stnType")
	private String stnType;
	
	
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
		    fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<LineMaster> lineMasterList;
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
		    fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<GateArray> gateArrayList;
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
		    fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<HardwareMaster> hardwareMasterList;
	
	
	public List<HardwareMaster> getHardwareMasterList() {
		return hardwareMasterList;
	}

	public void setHardwareMasterList(List<HardwareMaster> hardwareMasterList) {
		this.hardwareMasterList = hardwareMasterList;
	}

	public List<GateArray> getGateArrayList() {
		return gateArrayList;
	}

	public void setGateArrayList(List<GateArray> gateArrayList) {
		this.gateArrayList = gateArrayList;
	}

	@Column(name = "intchngLine")
	private String intchngLine;
	
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
		    fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SMID", referencedColumnName = "id")
	private List<PlatformMaster> platformMasterList;
	
	/*
	 * @Column(name = "noOfPlatforms") private Long NoOfPlatforms;
	 */
	
	public List<PlatformMaster> getPlatformMasterList() {
		return platformMasterList;
	}

	public void setPlatformMasterList(List<PlatformMaster> platformMasterList) {
		this.platformMasterList = platformMasterList;
	}

	@Column(name ="orgLine")
	private String orgLine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStnId() {
		return stnId;
	}

	public void setStnId(Integer stnId) {
		this.stnId = stnId;
	}

	public String getStnName() {
		return stnName;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}

	public String getStnAddress() {
		return stnAddress;
	}

	public void setStnAddress(String stnAddress) {
		this.stnAddress = stnAddress;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Long getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(Long gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public String getStnBrandName() {
		return stnBrandName;
	}

	public void setStnBrandName(String stnBrandName) {
		this.stnBrandName = stnBrandName;
	}

	public List<EmergencyContact> getEmergencyContactList() {
		return emergencyContactList;
	}

	public void setEmergencyContactList(List<EmergencyContact> emergencyContactList) {
		this.emergencyContactList = emergencyContactList;
	}

	public List<SecurityContact> getSecurityContactList() {
		return securityContactList;
	}

	public void setSecurityContactList(List<SecurityContact> securityContactList) {
		this.securityContactList = securityContactList;
	}
	
	
	public String getStnType() {
		return stnType;
	}

	public void setStnType(String stnType) {
		this.stnType = stnType;
	}
	
	

	public List<LineMaster> getLineMasterList() {
		return lineMasterList;
	}

	public void setLineMasterList(List<LineMaster> lineMasterList) {
		this.lineMasterList = lineMasterList;
	}

	public String getIntchngLine() {
		return intchngLine;
	}

	public void setIntchngLine(String intchngLine) {
		this.intchngLine = intchngLine;
	}


	public String getOrgLine() {
		return orgLine;
	}

	public void setOrgLine(String orgLine) {
		this.orgLine = orgLine;
	}

		
	
}
