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
@Table(name = "EmergencyContact")
public class EmergencyContact implements Serializable{

	
	private static final long serialVersionUID = -2587885949221294732L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "fName")
	private String fName;
	
	@Column(name = "lName")
	private String lName;
	
	@Column(name = "mailAddress")
	private String mailAddress;
	
	@Column(name = "contactNum")
	private String contactNum;
	
	
	@ManyToOne
	@JoinColumn(name="MetroDetailID", nullable=false) 
	private MetroDetailsMaster metroDetailsMaster;
	
	@ManyToOne
	@JoinColumn(name="DepotDetailID", nullable=false) 
	private DepotDetail depotDetailMaster;
	
	@ManyToOne
	@JoinColumn(name="SMID", nullable=false) 
	private StationMasterEntity stationMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public MetroDetailsMaster getMetroDetailsMaster() {
		return metroDetailsMaster;
	}

	public void setMetroDetailsMaster(MetroDetailsMaster metroDetailsMaster) {
		this.metroDetailsMaster = metroDetailsMaster;
	}

	public DepotDetail getDepotDetailMaster() {
		return depotDetailMaster;
	}

	public void setDepotDetailMaster(DepotDetail depotDetailMaster) {
		this.depotDetailMaster = depotDetailMaster;
	}
	
	
}
