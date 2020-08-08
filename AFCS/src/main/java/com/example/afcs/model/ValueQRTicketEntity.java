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
@Table(name = "ValueQRTicket")
public class ValueQRTicketEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6832041381206592675L;
	
 	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
	private Long id;
	
 	@Column(name = "amount")
	private Long amount;
	
 	@Column(name = "paymentMode")
	private String paymentMode;
	
 	@Column(name = "imei")
	private String imei;
	
 	@Column(name = "ipAddress")
	private String ipAddress;
	
 	@Column(name = "ticketId")
	private String ticketId;
	
 	@Column(name = "fullTicketNo")
	private String fullTicketNo;
	
 	@Column(name = "qrTicketHash")
	private String qrTicketHash;
	
 	@Column(name = "paymentStatus")
	private String paymentStatus;
	
 	@Column(name = "ticketStatus")
	private String ticketStatus;
	
 	@Column(name = "ticketValidity")
	private String ticketValidity;
	
 	@Column(name = "issueDate")
	private Date issueDate;
	
 	@Column(name = "issueTime")
	private String issueTime;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getFullTicketNo() {
		return fullTicketNo;
	}

	public void setFullTicketNo(String fullTicketNo) {
		this.fullTicketNo = fullTicketNo;
	}

	public String getQrTicketHash() {
		return qrTicketHash;
	}

	public void setQrTicketHash(String qrTicketHash) {
		this.qrTicketHash = qrTicketHash;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getTicketValidity() {
		return ticketValidity;
	}

	public void setTicketValidity(String ticketValidity) {
		this.ticketValidity = ticketValidity;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	
	
	
}
