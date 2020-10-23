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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PlatformMaster")
public class PlatformMaster implements Serializable {

	private static final long serialVersionUID = -4857135028982962802L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "pNo")
	private String pNo;
	
	@Column(name = "pName")
	private String pName;
	
		
	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
		    fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "PFMID", referencedColumnName = "id")
	private List<LineMaster> lineMasterList;
	
	@ManyToOne
	@JoinColumn(name="SMID", nullable=false) 
	private StationMasterEntity stationMaster;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public List<LineMaster> getLineMasterList() {
		return lineMasterList;
	}

	public void setLineMasterList(List<LineMaster> lineMasterList) {
		this.lineMasterList = lineMasterList;
	}

	public StationMasterEntity getStationMaster() {
		return stationMaster;
	}

	public void setStationMaster(StationMasterEntity stationMaster) {
		this.stationMaster = stationMaster;
	}
	
	
}
