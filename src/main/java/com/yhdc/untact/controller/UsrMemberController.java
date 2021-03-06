package com.yhdc.untact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhdc.untact.dto.Member;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.dto.Rq;
import com.yhdc.untact.service.GenFileService;
import com.yhdc.untact.service.MemberService;
import com.yhdc.untact.util.Util;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GenFileService genFileService;

	// MYPAGE
	@RequestMapping("/mpaUsr/member/mypage")
	public String showMypage(HttpServletRequest req) {
		return "mpaUsr/member/mypage";
	}

	// LOGIN
	@RequestMapping("/usr/member/login")
	public String showLOgin(HttpServletRequest req) {
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	public String doLogin(HttpServletRequest req, HttpSession session, String loginId, String loginPw,
			String redirectUri) {

		if (Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Util.msgAndBack(req, loginId + "는 존재하지 않는 아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니.");
		}

		session.setAttribute("loggedInMemberId", member.getId());
		session.setAttribute("loggedInMemberJsonStr", member.toJsonStr());

		String msg = "환영합니다.";

		boolean needToChangePassword = memberService.needToChangePassword(member.getId());

		if (needToChangePassword) {
			msg = "현재 비밀번호를 사용한지 " + memberService.getNeedToChangePassFreeDays() + "일이 지났습니다. 비밀번호를 변경해주세요.";
			redirectUri = "/usr/member/mypage";
		}
		
		boolean isUsingTempPassword = memberService.UsingTempPassword(member.getId());
		
		if(isUsingTempPassword) {
			msg = "임시 비밀번호를 변경해주세요.";
            redirectUri = "/usr/member/mypage";
		}

		return Util.msgAndReplace(req, msg, redirectUri);
	}

	// LOGOUT
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpServletRequest req, HttpSession session) {
		session.removeAttribute("loggedInMemberId");

		String msg = "로그아웃 되었습니다.";
		return Util.msgAndReplace(req, msg, "/");
	}

	// JOIN
	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname,
			String cellphoneNo, String email) {

		Member oldMember = memberService.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return Util.msgAndBack(req, loginId + "는 이미 사용중인 아이디 입니다.");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return Util.msgAndBack(req, joinRd.getMsg());
		}

		return Util.msgAndReplace(req, joinRd.getMsg(), "/");
	}

	// FIND LOGIN ID
	@RequestMapping("/usr/member/findLoginId")
	public String showFindLoginId(HttpServletRequest req) {
		return "usr/member/findLoginId";
	}

	@RequestMapping("/usr/member/doFindLoginId")
	public String doFindLoginId(HttpServletRequest req, String name, String email, String redirectUri) {

		if (Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		return Util.msgAndBack(req, String.format("회원님의 아이디는 '%s' 입니다.", member.getLoginId()));
	}

	// FIND LOGIN PASSWORD
	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPw(HttpServletRequest req) {
		return "/usr/member/findLoginPw";
	}

	@RequestMapping("/usr/member/doFindLoginPw")
	public String doFindLoginPw(HttpServletRequest req, String loginId, String name, String email, String redirectUri) {

		if (Util.isEmpty(redirectUri)) {
			redirectUri = "/";
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getName().equals(name) == false) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		if (member.getEmail().equals(email) == false) {
			return Util.msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
		}

		ResultData notifyTempLoginPwByEmailRs = memberService.notifyTempLoginPwByEmail(member);

		return Util.msgAndReplace(req, notifyTempLoginPwByEmailRs.getMsg(), redirectUri);
	}

	// EDIT
	@RequestMapping("/mpaUsr/member/edit")
	public String showEdit(HttpServletRequest req, String checkPasswordAuthCode) {

		Member loggedInMember = ((Rq) req.getAttribute("rq")).getLoggedInMember();

		ResultData checkVMPAuthCodeRD = memberService.checkVMPAuthCodeRD(loggedInMember.getId(), checkPasswordAuthCode);

		if (checkVMPAuthCodeRD.isFail()) {
			return Util.msgAndBack(req, checkVMPAuthCodeRD.getMsg());
		}

		log.debug("checkVMPAuthCodeRd : " + checkVMPAuthCodeRD);

		return "mpaUsr/member/modify";
	}

	@RequestMapping("/mpaUsr/member/doEdit")
	public String doEdit(HttpServletRequest req, String loginPw, String name, String nickname, String cellphoneNo,
			String email, String checkPasswordAuthCode) {

		Member loggedInMember = ((Rq) req.getAttribute("rq")).getLoggedInMember();

		ResultData checkVCPassAuthCodeRD = memberService.checkVMPAuthCodeRD(loggedInMember.getId(),
				checkPasswordAuthCode);

		if (checkVCPassAuthCodeRD.isFail()) {
			return Util.msgAndBack(req, checkVCPassAuthCodeRD.getMsg());
		}

		if (loginPw != null && loginPw.trim().length() == 0) {
			loginPw = null;
		}

		int id = ((Rq) req.getAttribute("rq")).getLoggedInMemberId();
		ResultData modifyRd = memberService.edit(id, loginPw, name, nickname, cellphoneNo, email);

		if (modifyRd.isFail()) {
			return Util.msgAndBack(req, modifyRd.getMsg());
		}

		return Util.msgAndReplace(req, modifyRd.getMsg(), "/");
	}

	// CHECK PASSWORD
	@RequestMapping("/mpaUsr/member/checkPassword")
	public String showCheckPassword(HttpServletRequest req) {
		return "mpaUsr/member/checkPassword";
	}

	@RequestMapping("/mpaUsr/member/doCheckPassword")
	public String doCheckPassword(HttpServletRequest req, String loginPw, String redirectUri) {
		Member loggedInMember = ((Rq) req.getAttribute("rq")).getLoggedInMember();

		if (loggedInMember.getLoginPw().equals(loginPw) == false) {
			return Util.msgAndBack(req, "비밀번호가 일치하지 않습니다.");
		}

		String authCode = memberService.genCheckPassAuthCode(loggedInMember.getId());

		redirectUri = Util.getNewUri(redirectUri, "checkPasswordAuthCode", authCode);

		return Util.msgAndReplace(req, "", redirectUri);
	}
}
