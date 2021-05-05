package com.yhdc.untact.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yhdc.untact.dto.Member;
import com.yhdc.untact.dto.Rq;
import com.yhdc.untact.service.MemberService;
import com.yhdc.untact.util.Util;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        Map<String, String> paramMap = Util.getParamMap(req);

        HttpSession session = req.getSession();

        Member loggedInMember = null;
        int loggedInMemberId = 0;

        if (session.getAttribute("loginedMemberId") != null) {
            loggedInMemberId = (int) session.getAttribute("loginedMemberId");
        }

        if (loggedInMemberId != 0) {
        	String loggedInMemberJsonStr = (String) session.getAttribute("loggedInMemberJsonStr");
        	loggedInMember = Util.fromJsonStr(loggedInMemberJsonStr, Member.class);
        }

        String currentUrl = req.getRequestURI();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            currentUrl += "?" + queryString;
        }
        
        boolean needToChangePassword = false;
        
        if (loggedInMember != null) {
        	if (session.getAttribute("needToChangePassword") == null) {
        		needToChangePassword = memberService.needToChangePassword(loggedInMember.getId());
        		
        		session.setAttribute("needToChangePassword", needToChangePassword);
        	}
        	needToChangePassword = (boolean) session.getAttribute("needToChangePassword");
        }

        req.setAttribute("rq", new Rq(loggedInMember, currentUrl, paramMap, needToChangePassword));

        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}
