package com.yhdc.untact.dto;

public class Rq {
	private Member loggedInMember;
	
	public Rq(Member loggedInMember) {
		this.loggedInMember = loggedInMember; 
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
	
	public String getLoggedInMemberNuckname() {
		if (isNotLoggedIn()) return "";
		return loggedInMember.getNickname();
	}
}
