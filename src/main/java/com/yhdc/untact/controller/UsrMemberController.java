package com.yhdc.untact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhdc.untact.dto.Member;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.service.MemberService;
import com.yhdc.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;
	
	//LOGIN
	@RequestMapping("/usr/member/login")
	public String showLOgin(HttpServletRequest req) {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	public String doLogin(HttpServletRequest req, HttpSession session, String loginId, String loginPw, String redirectUrl) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.msgAndBack(req, loginId + "는 존재하지 않는 아이디 입니다.");
		}
		
		if (member.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니.");
		}
		
		//HttpSession session = req.getSession(); 
		session.setAttribute("loggedInMemberId", member.getId());
		
		String msg = "환영합니다.";
		return Util.msgAndReplace(req,  msg, redirectUrl);
	}
	
	//LOGOUT
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("loggedInMemberId");
		
		String msg = "로그아웃 되었습니다.";
		return Util.msgAndReplace(req, msg, "/");
	}
	
	
	//JOIN
	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
		public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member existingMember = memberService.getMemberByLoginId(loginId);
		
		if (existingMember != null) {
			return Util.msgAndBack(req, loginId + "는 이미 사용중인 아이디 입니다.");
		}	
	
		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if (joinRd.isFail()) {
			return Util.msgAndBack(req, joinRd.getMsg());		
		}
		
		return Util.msgAndReplace(req, joinRd.getMsg(), "/");
	}
}
