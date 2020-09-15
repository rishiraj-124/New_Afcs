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
@Table(name = "PassengerMaster")
public class PassengerMasterEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6275634845981871370L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "paxTypeId")
	private Integer paxTypeId;
	
	@Column(name ="paxType")
	private String paxType;
	
	@Column(name="description")
	private String desc;
	
	@Column(name="criteria")
	private String criteria;
	
	@Column(name="docReq")
	private String docReq;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPaxTypeId() {
		return paxTypeId;
	}

	public void setPaxTypeId(Integer paxTypeId) {
		this.paxTypeId = paxTypeId;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getDocReq() {
		return docReq;
	}

	public void setDocReq(String docReq) {
		this.docReq = docReq;
	}
	
	
}
