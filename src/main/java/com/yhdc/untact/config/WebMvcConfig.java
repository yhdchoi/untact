package com.yhdc.untact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yhdc.untact.interceptor.BeforeActionInterceptor;
import com.yhdc.untact.interceptor.NeedToLoginInterceptor;
import com.yhdc.untact.interceptor.NeedToLogoutInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//CALLING beforeActionInterceptor
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	@Autowired
	NeedToLoginInterceptor needToLoginInterceptor;
	
	@Autowired
	NeedToLogoutInterceptor needToLogoutInterceptor;
	
	//APPLYING INTERCEPTOR
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/resource/**")
				.excludePathPatterns("/error");
		
		registry.addInterceptor(needToLoginInterceptor)
				.addPathPatterns("/usr/article/write")
				.addPathPatterns("/usr/article/doWrite")
				.addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/article/modify")
				.addPathPatterns("/usr/article/doModify");
		
		registry.addInterceptor(needToLogoutInterceptor)
				.addPathPatterns("/usr/member/login")
				.addPathPatterns("/usr/member/doLogin")
				.addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/doJoin")
				.addPathPatterns("/usr/member/findLoginId")
				.addPathPatterns("/usr/member/doFindLoginId")
				.addPathPatterns("/usr/member/findLoginPw")
				.addPathPatterns("/usr/member/doFindLoginPw");
	}
		
}
