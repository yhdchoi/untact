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
    
    private boolean isAjax(HttpServletRequest req) {
        String[] pathBits = req.getRequestURI().split("/");

        String controllerTypeCode = "";
        String controllerSubject = "";
        String controllerActName = "";

        if (pathBits.length > 1) {
            controllerTypeCode = pathBits[1];
        }

        if (pathBits.length > 2) {
            controllerSubject = pathBits[2];
        }

        if (pathBits.length > 3) {
            controllerActName = pathBits[3];
        }

        boolean isAjax = false;

        String isAjaxParameter = req.getParameter("isAjax");

        if ( isAjax == false ) {
            if ( controllerActName.startsWith("get") ) {
                isAjax = true;
            }
        }

        if ( isAjax == false ) {
            if (controllerActName.endsWith("Ajax")) {
                isAjax = true;
            }
        }

        if ( isAjax == false ) {
            if (isAjaxParameter != null && isAjaxParameter.equals("Y")) {
                isAjax = true;
            }
        }

        return isAjax;
    }
    

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

        String currentUri = req.getRequestURI();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            currentUri += "?" + queryString;
        }
        
        boolean needToChangePassword = false;
        
        if (loggedInMember != null) {
        	if (session.getAttribute("needToChangePassword") == null) {
        		needToChangePassword = memberService.needToChangePassword(loggedInMember.getId());
        		
        		session.setAttribute("needToChangePassword", needToChangePassword);
        	}
        	needToChangePassword = (boolean) session.getAttribute("needToChangePassword");
        }

        req.setAttribute("rq", new Rq(isAjax(req), loggedInMember, currentUri, paramMap, needToChangePassword));

        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}
