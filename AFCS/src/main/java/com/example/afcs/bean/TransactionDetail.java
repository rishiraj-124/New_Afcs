package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class TransactionDetail implements ValidationBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1781831426908477276L;
	
	private String txnId;
	
	private String txnAmnt;
	
	private String paymentMode;
	
	private String txnTime;
	
	private String txnType;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getTxnAmnt() {
		return txnAmnt;
	}

	public void setTxnAmnt(String txnAmnt) {
		this.txnAmnt = txnAmnt;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	
	

}
