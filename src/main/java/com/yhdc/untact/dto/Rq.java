package com.yhdc.untact.dto;

import com.yhdc.untact.util.Util;

public class Rq {
	private Member loggedInMember;
	private String currentUri;
	
	public Rq(Member loggedInMember, String currentUri) {
		this.loggedInMember = loggedInMember; 
		this.currentUri = currentUri.split("\\?")[0];
		this.currentUri = currentUri;
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
	

	private String getCurrentUri() {
		return currentUri;
	}
	
	public String getEncodedCurrentUri() {
		return Util.getUriEncoded(getCurrentUri());
	}
	
	private boolean isLoginPage() {
		return currentUri.equals("/usr/member/login");
	}
	
	public String getLoginPageUri() {
		String afterLoginUri;
		
		if(isLoginPage()) {
			afterLoginUri = "";			
		} else {
			afterLoginUri = getEncodedCurrentUri();
		}
		
		return "../member/login?afterLoginUri=" + afterLoginUri;
	}
}
