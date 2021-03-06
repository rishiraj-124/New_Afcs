package com.example.afcs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PassMaster")
public class PassMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8836208152345737040L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name ="passId")
	private Integer passId;
	
	@Column(name ="passPeriod")
	private String passPeriod;
	
	@Column(name ="passName")
	private String passName;
	
	@Column(name = "tripAllowed")
	private String tripAllowed;
	
	@Column(name ="passAmt")
	private Integer passAmt;
	
	@Column(name ="validity")
	private String validity;
	
	@Column(name ="paxType")
	private String paxType;
	
	@Column(name ="distance_Src_Dest")
	private Integer distance_Src_Dest;
	
	@Column(name ="doc_Reqd")
	private String doc_Reqd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPassId() {
		return passId;
	}

	public void setPassId(Integer passId) {
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

	public Integer getPassAmt() {
		return passAmt;
	}

	public void setPassAmt(Integer passAmt) {
		this.passAmt = passAmt;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public Integer getDistance_Src_Dest() {
		return distance_Src_Dest;
	}

	public void setDistance_Src_Dest(Integer distance_Src_Dest) {
		this.distance_Src_Dest = distance_Src_Dest;
	}

	public String getDoc_Reqd() {
		return doc_Reqd;
	}

	public void setDoc_Reqd(String doc_Reqd) {
		this.doc_Reqd = doc_Reqd;
	}
}
