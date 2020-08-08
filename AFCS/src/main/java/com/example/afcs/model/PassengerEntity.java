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
@Table(name = "Passenger")
public class PassengerEntity implements Serializable {

	private static final long serialVersionUID = -5481183797927706218L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
	private long id;
	
	@Column(name= "pssType")
	private String pssType;
	
	@Column(name= "noOfTkt")
	private Integer noOfTkt;
	
	@Column(name= "tktAmount")
	private Long tktAmount;
	
	@Column(name= "discount")
	private Integer discount;
	
	@Column(name= "amtPaid")
	private Long amtPaid;
	
	@Column(name= "totalAmt")
	private Long totalAmt;
	
	
	  @ManyToOne
	  @JoinColumn(name="sjId", nullable=false) 
	  private SingleJourneyEntitiy singleJourneyEntitiy;
	 

	public PassengerEntity(){}
	


	public PassengerEntity(String pssType, Integer noOfTkt, Long tktAmount, Integer discount, Long amtPaid,
			Long totalAmt, SingleJourneyEntitiy singleJourneyEntitiy) {
		super();
		//this.id = id;
		this.pssType = pssType;
		this.noOfTkt = noOfTkt;
		this.tktAmount = tktAmount;
		this.discount = discount;
		this.amtPaid = amtPaid;
		this.totalAmt = totalAmt;
		this.singleJourneyEntitiy = singleJourneyEntitiy;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPssType() {
		return pssType;
	}

	public void setPssType(String pssType) {
		this.pssType = pssType;
	}

	public Integer getNoOfTkt() {
		return noOfTkt;
	}

	public void setNoOfTkt(Integer noOfTkt) {
		this.noOfTkt = noOfTkt;
	}

	public Long getTktAmount() {
		return tktAmount;
	}

	public void setTktAmount(Long tktAmount) {
		this.tktAmount = tktAmount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Long getAmtPaid() {
		return amtPaid;
	}

	public void setAmtPaid(Long amtPaid) {
		this.amtPaid = amtPaid;
	}

	public Long getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Long totalAmt) {
		this.totalAmt = totalAmt;
	}

	
	  public SingleJourneyEntitiy getSingleJourneyEntitiy() { return
	  singleJourneyEntitiy; }
	  
	  public void setSingleJourneyEntitiy(SingleJourneyEntitiy
	  singleJourneyEntitiy) { this.singleJourneyEntitiy = singleJourneyEntitiy; }
	 
	
	
	
	
}
