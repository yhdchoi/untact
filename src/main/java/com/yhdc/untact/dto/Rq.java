package com.yhdc.untact.dto;

import java.util.Map;

import com.yhdc.untact.util.Util;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isAjax;
	private String currentUrl;
	@Getter
    private String currentUri;
    private Member loggedInMember;
    private Map<String, String> paramMap;
    @Getter
    private boolean needToChangePassword;

    public Rq(boolean isAjax, Member loggedInMember, String currentUri, Map<String, String> paramMap, boolean needToChangePassword) {
    	this.isAjax = isAjax;
    	this.loggedInMember = loggedInMember;
        this.currentUrl = currentUri.split("\\?")[0];
        this.currentUri = currentUri;
        this.paramMap = paramMap;
        this.needToChangePassword = needToChangePassword;
    }
    
    public String getParamJsonStr() {
		return Util.toJsonStr(paramMap);
	}

    public boolean isLoggedIn() {
        return loggedInMember != null;
    }

    public boolean isNotLoggedIn() {
        return isLoggedIn() == false;
    }

    public int getLoggedInMemberId() {
        if (isNotLoggedIn()) return 0;

        return loggedInMember.getId();
    }

    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public String getEncodedCurrentUri() {
        return Util.getUriEncoded(getCurrentUri());
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public String getLoginPageUri() {
        String afterLoginUri;

        if (isLoginPage()) {
            afterLoginUri = Util.getUriEncoded(paramMap.get("afterLoginUri"));
        } else {
            afterLoginUri = getEncodedCurrentUri();
        }

        return "../member/login?afterLoginUri=" + afterLoginUri;
    }

    private boolean isLoginPage() {
        return currentUrl.equals("/mpaUsr/member/login");
    }
}
