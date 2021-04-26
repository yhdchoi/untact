package com.yhdc.untact.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsrMemberController {
	
	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Map doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		return Util.mapOf("loginId", loginId, "loginPw", loginPw, "name", name, "nickname", nickname, "cellphoneNo", cellphoneNo, "email", email);
	}
}
