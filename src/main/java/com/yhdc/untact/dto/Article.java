package com.yhdc.untact.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String content;
	private boolean blindStatus;
    private String blindDate;
    private boolean delStatus;
    private String delDate;
    private int hitCount;
    private int repliesCount;
    private int likeCount;
    private int dislikeCount;
    private Map<String, Object> extra;
    private String extra_writerName;
    
    public String getContentForPrint() {
    	String contentForPrint = content.replaceAll("\r\n", "<br>");
    	contentForPrint = content.replaceAll("\r", "<br>");
    	contentForPrint = content.replaceAll("\n", "<br>");
    	
    	return contentForPrint;
    }
}
