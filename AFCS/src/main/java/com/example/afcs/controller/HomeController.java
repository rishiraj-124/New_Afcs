package com.example.afcs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.afcs.bean.LoginRequest;
import com.example.afcs.main.ApplicationPropertyReader;
import com.example.afcs.service.LoginService;
import com.example.afcs.service.TicketingService;

@Controller
@ComponentScan({"com.example.afcs.bean","com.example.afcs.service"})
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	LoginRequest loginRequest;
	
	@Autowired
	LoginService loginService;
	
	@Autowired 
	TicketingService ticketingService;
	 
	
	
	@Autowired
	private ApplicationPropertyReader applicationPropertyReader;
	
	@GetMapping(path="/index")
	public ModelAndView indexMethod(){
		System.out.println("*************home welcome*********************************");
		return new ModelAndView("index");
	}
	
	@GetMapping(path="/submitLogin")
	public ModelAndView customerLogin() {
		System.out.println("************Submit Login********************************");
		
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	
	
	
}
