package com.example.afcs.model;

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
@Table(name = "HardwareMaster")
public class HardwareMaster {
	
   	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   	
   	@Column(name = "hardwareName")
   	private String hardwareName;
   	
   	@Column(name = "description")
   	private String desc;
   	
   	@Column(name = "detailDescription")
   	private String detailDesc;
   	
  
   	
    @ManyToOne
	@JoinColumn(name="IMID", nullable=false) 
	private InventoryMaster inventoryMaster;
    
    @ManyToOne
	@JoinColumn(name="SMID", nullable=false) 
	private StationMasterEntity stationMaster;

 	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public InventoryMaster getInventoryMaster() {
		return inventoryMaster;
	}

	public void setInventoryMaster(InventoryMaster inventoryMaster) {
		this.inventoryMaster = inventoryMaster;
	}

	public String getHardwareName() {
		return hardwareName;
	}

	public void setHardwareName(String hardwareName) {
		this.hardwareName = hardwareName;
	}

	public StationMasterEntity getStationMaster() {
		return stationMaster;
	}

	public void setStationMaster(StationMasterEntity stationMaster) {
		this.stationMaster = stationMaster;
	}
	
	

}
