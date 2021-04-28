package com.yhdc.untact.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yhdc.untact.dto.Rq;
import com.yhdc.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NeedToLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Rq rq = (Rq) req.getAttribute("rq");

        if (rq.isNotLoggedIn()) {
            resp.setContentType("text/html; charset=UTF-8");
            String afterLoginUri = rq.getEncodedCurrentUri();
            resp.getWriter().append(Util.msgAndReplace("로그인 후 이용해주세요.", "../member/login?afterLoginUri=" + afterLoginUri));
            return false;
        }

        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}
