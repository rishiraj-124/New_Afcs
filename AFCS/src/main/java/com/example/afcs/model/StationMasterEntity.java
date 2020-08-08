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
	
	@Column(name ="isInterChange")
	private Boolean isInterChange;
	
	@Column(name = "intchngLine")
	private String intchngLine;
	
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

	public Boolean getIsInterChange() {
		return isInterChange;
	}

	public void setIsInterChange(Boolean isInterChange) {
		this.isInterChange = isInterChange;
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
