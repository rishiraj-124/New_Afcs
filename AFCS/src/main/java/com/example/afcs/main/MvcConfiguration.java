
package com.example.afcs.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		//bean.setSuffix(".html");
		return bean;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/resources/**")
	      .addResourceLocations("/resources/")
	      .setCachePeriod(3600)
	      .resourceChain(true)
	      .addResolver(new PathResourceResolver());
	}
}
