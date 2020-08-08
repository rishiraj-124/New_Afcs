package com.example.afcs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.afcs"})
@ComponentScan(basePackages = {"com.example.afcs"})
public class AFCSApplication extends SpringBootServletInitializer{

		public static void main(String[] args) {
			SpringApplication.run(AFCSApplication.class, args);
		}
		@Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

	        return application.sources(AFCSApplication.class);
	
	    }
		
		
		
}
