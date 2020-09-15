package com.example.afcs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.afcs.bean.LoginRequest;
import com.example.afcs.main.ApplicationPropertyReader;
import com.example.afcs.service.LoginService;
import com.example.afcs.service.TicketingService;

@Controller
@ComponentScan({"com.example.afcs.bean","com.example.afcs.service"})
public class MastersController {
	
	private static final Logger log = LoggerFactory.getLogger(MastersController.class);
	@Autowired
	LoginRequest loginRequest;
	
	@Autowired
	LoginService loginService;
	
	@Autowired 
	TicketingService ticketingService;
	 
	
	
	@Autowired
	private ApplicationPropertyReader applicationPropertyReader;
	

	@GetMapping(path="/gateMaster")
	public ModelAndView gateMaster(){
		System.out.println("*************gateMaster*********************************");
		return new ModelAndView("gateMaster");
	}
	
	@GetMapping(path="/commuterMaster")
	public ModelAndView commuterMaster(){
		System.out.println("*************commuterMaster*********************************");
		return new ModelAndView("commuterMaster");
	}
	
	
	@GetMapping(path="/lineMaster")
	public ModelAndView lineMaster(){
		System.out.println("*************lineMaster*********************************");
		return new ModelAndView("lineMaster");
	}
	
	
	
}
