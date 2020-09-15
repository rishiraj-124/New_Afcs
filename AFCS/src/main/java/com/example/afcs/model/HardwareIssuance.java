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
@Table(name = "HardwareIssuance")
public class HardwareIssuance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -286136887671339L;
	
   	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   	
   	@Column(name = "atStation")
   	private String atStation;
   	
   	@Column(name = "gateArray")
   	private String[]  gateArray;
   	
	@ManyToOne
	@JoinColumn(name="IMID", nullable=false) 
	private InventoryMaster inventoryMaster;
   	
   	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
   	
   	public String getAtStation() {
		return atStation;
	}

	public void setAtStation(String atStation) {
		this.atStation = atStation;
	}

	public String[] getGateArray() {
		return gateArray;
	}

	public void setGateArray(String[] gateArray) {
		this.gateArray = gateArray;
	}



	

	public InventoryMaster getInventoryMaster() {
		return inventoryMaster;
	}

	public void setInventoryMaster(InventoryMaster inventoryMaster) {
		this.inventoryMaster = inventoryMaster;
	}
}
