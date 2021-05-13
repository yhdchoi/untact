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
				.addPathPatterns("/usr/article/edit")
				.addPathPatterns("/usr/article/doEdit")				
				.addPathPatterns("/usr/reply/doWrite")
				.addPathPatterns("/usr/reply/doDelete")
				.addPathPatterns("/usr/reply/edit")
				.addPathPatterns("/usr/reply/doEdit")				
				.addPathPatterns("/usr/member/edit")
				.addPathPatterns("/usr/member/doEdit")
				.addPathPatterns("/usr/member/checkPassword")
				.addPathPatterns("/usr/member/doCheckPassword");
		
		registry.addInterceptor(needToLogoutInterceptor)
				.addPathPatterns("/mpaUsr/member/findLoginId")
		        .addPathPatterns("/mpaUsr/member/doFindLoginId")
		        .addPathPatterns("/mpaUsr/member/findLoginPw")
		        .addPathPatterns("/mpaUsr/member/doFindLoginPw")
		        .addPathPatterns("/mpaUsr/member/login")
		        .addPathPatterns("/mpaUsr/member/doLogin")
		        .addPathPatterns("/mpaUsr/member/join")
		        .addPathPatterns("/mpaUsr/member/doJoin")
		        .addPathPatterns("/mpaUsr/member/findLoginId")
		        .addPathPatterns("/mpaUsr/member/doFindLoginId")
		        .addPathPatterns("/mpaUsr/member/findLoginPw")
		        .addPathPatterns("/mpaUsr/member/doFindLoginPw");
	}
		
}
