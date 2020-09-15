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
@Table(name = "MetroDetailsMaster")
public class MetroDetailsMaster implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "regdAddress")
	private String regdAddress;
	
	@Column(name = "minFare")
	private Long minFare;
	
	@Column(name = "maxTimeAllowed")
	private String maxTimeAllowed;
	
	@Column(name = "maxPenaltyFare")
	private Long maxPenaltyFare;
	
	@Column(name = "sjTktValidityTime")
	private String sjTktValidityTime;
	
	@Column(name = "rjTktValidityTime")
	private String rjTktValidityTime;
	
	@Column(name = "allwdHoldingTmWthNoFare")
	private String allwdHoldingTmWthNoFare;
	
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "MetroDetailID", referencedColumnName = "id")
	private List<DepotDetail> depotDetailList;
	
 	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "MetroDetailID", referencedColumnName = "id")
	private List<EmergencyContact> emergencyContactList;
   	
   	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "MetroDetailID", referencedColumnName = "id")
	private List<SecurityContact> securityContactList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegdAddress() {
		return regdAddress;
	}

	public void setRegdAddress(String regdAddress) {
		this.regdAddress = regdAddress;
	}

	public Long getMinFare() {
		return minFare;
	}

	public void setMinFare(Long minFare) {
		this.minFare = minFare;
	}

	public String getMaxTimeAllowed() {
		return maxTimeAllowed;
	}

	public void setMaxTimeAllowed(String maxTimeAllowed) {
		this.maxTimeAllowed = maxTimeAllowed;
	}

	public Long getMaxPenaltyFare() {
		return maxPenaltyFare;
	}

	public void setMaxPenaltyFare(Long maxPenaltyFare) {
		this.maxPenaltyFare = maxPenaltyFare;
	}

	public String getSjTktValidityTime() {
		return sjTktValidityTime;
	}

	public void setSjTktValidityTime(String sjTktValidityTime) {
		this.sjTktValidityTime = sjTktValidityTime;
	}

	public String getRjTktValidityTime() {
		return rjTktValidityTime;
	}

	public void setRjTktValidityTime(String rjTktValidityTime) {
		this.rjTktValidityTime = rjTktValidityTime;
	}

	public String getAllwdHoldingTmWthNoFare() {
		return allwdHoldingTmWthNoFare;
	}

	public void setAllwdHoldingTmWthNoFare(String allwdHoldingTmWthNoFare) {
		this.allwdHoldingTmWthNoFare = allwdHoldingTmWthNoFare;
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
   	
   	

}
