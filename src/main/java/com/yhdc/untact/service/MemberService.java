package com.yhdc.untact.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yhdc.untact.dao.MemberDao;
import com.yhdc.untact.dto.Member;
import com.yhdc.untact.dto.ResultData;
import com.yhdc.untact.util.Util;

@Service
public class MemberService {
	
	@Autowired
	private AttrService attrService;
	
    @Autowired
    private MailService mailService;

    @Value("${custom.siteMainUri}")
    private String siteMainUri;
    @Value("${custom.siteName}")
    private String siteName;

    @Autowired
    private MemberDao memberDao;

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public ResultData join(String loginId, String loginPw, String name, String nickname, 
    		String cellphoneNo, String email) {
    	
        memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);
        int id = memberDao.getLastInsertId();

        return new ResultData("S-1", "회원가입이 완료되었습니다.", "id", id);
    }

    public Member getMemberById(int id) {
        return memberDao.getMemberById(id);
    }

    public Member getMemberByNameAndEmail(String name, String email) {
        return memberDao.getMemberByNameAndEmail(name, email);
    }

    public ResultData notifyTempLoginPwByEmail(Member actor) {
        String title = "[" + siteName + "] 임시 패스워드 발송";
        String tempPassword = Util.getTempPassword(6);
        String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
        body += "<a href=\"" + siteMainUri + "/mpaUsr/member/login\" target=\"_blank\">로그인 하러가기</a>";

        ResultData sendResultData = mailService.send(actor.getEmail(), title, body);

        if (sendResultData.isFail()) {
            return sendResultData;
        }

        tempPassword = Util.sha256(tempPassword);

        setTempPassword(actor, tempPassword);

        return new ResultData("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다.");
    }

    private void setTempPassword(Member actor, String tempPassword) {
        memberDao.edit(actor.getId(), tempPassword, null, null, null, null);
    }

    public ResultData edit(int id, String loginPw, String name, String nickname, String cellphoneNo, 
    		String email) {
    	
        memberDao.edit(id, loginPw, name, nickname, cellphoneNo, email);
        
        if (loginPw != null) {
        	attrService.remove("member", id, "extra", "useTempPassword");
        }

        return new ResultData("S-1", "회원정보가 수정되었습니다.", "id", id);
    }

	public ResultData checkVMPAuthCodeRD(int actorId, String checkPasswordAuthCode) {
		if (attrService.getValue("member__" + actorId + "__extra__checkPasswordAuthCode")
				.equals(checkPasswordAuthCode)) {
			
			return new ResultData("S-1", "유효한 키 입니다.");
		}
		return new ResultData("S-1", "유효하지 않은 키 입니다.");
	}
	
	public String genCheckPassAuthCode(int actorId) {
		String attrName = "member__" + actorId + "__extra__checkPasswordAuthCode";
		String authCode = UUID.randomUUID().toString();
		String expireDate = Util.getDateStrLater(60 * 60);
		
		attrService.setValue(attrName, authCode, expireDate);
		
		return authCode;
	}

	public boolean isUsingTempPassword(int actorId) {

		return attrService.getValue("member", actorId, "extra", "useTempPassword").equals("1");
	}
}
