package com.yhdc.untact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yhdc.untact.interceptor.BeforeActionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//CALLING beforeActionInterceptor
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	//APPLYING INTERCEPTOR
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/resource/**");
	}
		
}
