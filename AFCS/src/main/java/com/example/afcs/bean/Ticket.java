package com.example.afcs.bean;

import org.springframework.stereotype.Component;

@Component
public class Ticket {
	
	private int ticketTypeId;
	
	private String ticketType;

	public int getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	

}
