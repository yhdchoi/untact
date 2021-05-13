package com.yhdc.untact.dto;

import java.util.Map;

import com.yhdc.untact.util.Util;

import lombok.Data;

@Data
public class ResultData {
	private String resultCode;
	private String msg;
	private Map<String, Object> content;

	public ResultData(String resultCode, String msg, Object... args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.content = Util.mapOf(args);
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}
}
