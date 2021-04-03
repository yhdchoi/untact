package com.yhdc.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Article {
	private int id;
	private String regDate;
	private String upDate;
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
}
