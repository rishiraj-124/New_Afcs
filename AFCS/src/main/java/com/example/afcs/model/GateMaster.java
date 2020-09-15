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
@Table(name = "GateMaster")
public class GateMaster implements Serializable {

  	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
  	
  	@Column(name = "gateType")
  	private String gateType;
  	
  	@Column(name = "gateDesc")
  	private String gateDesc;

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

	public String getGateDesc() {
		return gateDesc;
	}

	public void setGateDesc(String gateDesc) {
		this.gateDesc = gateDesc;
	}
  	
  	
}
