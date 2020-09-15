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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "InventoryMaster")
@NamedQueries
({
	@NamedQuery(name="InventoryMaster.findBySerialNum", query="SELECT im FROM InventoryMaster im WHERE im.serialNo = :serialNo"),
	@NamedQuery(name="InventoryMaster.findByMacId", query="SELECT im FROM InventoryMaster im WHERE im.macId = :macId"),
})
public class InventoryMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7511168279683369272L;

   	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   	
   	@Column(name = "hardwareType")
   	private String hardwareType;
   	
   	@Column(name = "macId")
   	private String macId;
   	
   	@Column(name = "serialNo")
   	private Long serialNo;
   	
   	@Column(name = "application")
   	private String application;
   	
   	@Column(name = "vendor")
   	private String vendor;
   	
   	@Column(name = "warrantyExp")
   	private String warrantyExp;
   	
   	@Column(name = "power")
   	private String power;
   	
   	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "IMID", referencedColumnName = "id")
	private List<HardwareIssuance> hdIssuanceList;
   	
   	@OneToMany(targetEntity=PassengerEntity.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
 	@JoinColumn(name = "IMId", referencedColumnName = "id")
	private List<HardwareMaster> hdMasterList;
   	
   	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getWarrantyExp() {
		return warrantyExp;
	}

	public void setWarrantyExp(String warrantyExp) {
		this.warrantyExp = warrantyExp;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public List<HardwareIssuance> getHdIssuanceList() {
		return hdIssuanceList;
	}

	public void setHdIssuanceList(List<HardwareIssuance> hdIssuanceList) {
		this.hdIssuanceList = hdIssuanceList;
	}

	public List<HardwareMaster> getHdMasterList() {
		return hdMasterList;
	}

	public void setHdMasterList(List<HardwareMaster> hdMasterList) {
		this.hdMasterList = hdMasterList;
	}

	
   	
}
