package com.yhdc.untact.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yhdc.untact.dto.Member;
import com.yhdc.untact.dto.Rq;
import com.yhdc.untact.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		HttpSession session = req.getSession();
		
		Member loggedInMember = null;
		int loggedInMemberId = 0;
		
		if (session.getAttribute("loggedInMemberId") != null) {
			loggedInMemberId = (int) session.getAttribute("loggedInMemberId");
		}
		
		if ( loggedInMemberId != 0) {
			loggedInMember = memberService.getMemberByLoginId("loggedInMemberId");
		}
		
		String currentUrl = req.getRequestURI();
		String queryString = req.getQueryString();
		
		if (queryString != null && queryString.length() > 0) {
			currentUrl += "?" + queryString;
		}
		
		req.setAttribute("rq", new Rq(loggedInMember, currentUrl));
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
