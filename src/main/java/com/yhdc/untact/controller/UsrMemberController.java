package com.yhdc.untact.controller;

import javax.servlet.http.HttpServletRequest;

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
