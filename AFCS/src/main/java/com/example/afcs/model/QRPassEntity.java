package com.example.afcs.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QRPass")
public class QRPassEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1755954111966082468L;

 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
   
   	@Column(name = "userId")
	private String userId;
	
   	@Column(name = "tktBookingdt")
	private Date tktBookingdt;
	
   	@Column(name = "srcStnId")
	private Integer srcStnId;
	
   	@Column(name = "destStnId")
	private Integer destStnId;
	
   	@Column(name = "tktNo")
	private Long tktNo;
	
   	@Column(name = "custIpAddress")
	private String custIpAddress;
	
   	@Column(name = "custImei")
	private String custImei;
	
   	@Column(name = "passType")
	private String passType;
	
   	@Column(name = "passNo")
	private String passNo;
   	
   	@Column(name = "payMode")
	private String payMode;
	
   	@Column(name = "paidAmt")
	private String paidAmt;
	
   	@Column(name = "pmtId")
	private String pmtId;
   	
   	@Column(name = "qrTicketHash")
   	private String qrTicketHash;
   	
   	@Column(name = "status")
   	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTktBookingdt() {
		return tktBookingdt;
	}

	public void setTktBookingdt(Date tktBookingdt) {
		this.tktBookingdt = tktBookingdt;
	}

	public Integer getSrcStnId() {
		return srcStnId;
	}

	public void setSrcStnId(Integer srcStnId) {
		this.srcStnId = srcStnId;
	}

	public Integer getDestStnId() {
		return destStnId;
	}

	public void setDestStnId(Integer destStnId) {
		this.destStnId = destStnId;
	}

	public Long getTktNo() {
		return tktNo;
	}

	public void setTktNo(Long tktNo) {
		this.tktNo = tktNo;
	}

	public String getCustIpAddress() {
		return custIpAddress;
	}

	public void setCustIpAddress(String custIpAddress) {
		this.custIpAddress = custIpAddress;
	}

	public String getCustImei() {
		return custImei;
	}

	public void setCustImei(String custImei) {
		this.custImei = custImei;
	}

	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getPassNo() {
		return passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getPmtId() {
		return pmtId;
	}

	public void setPmtId(String pmtId) {
		this.pmtId = pmtId;
	}

	public String getQrTicketHash() {
		return qrTicketHash;
	}

	public void setQrTicketHash(String qrTicketHash) {
		this.qrTicketHash = qrTicketHash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   	
}
