package com.yhdc.untact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Article {
	private int id;
	private String regDate;
	private String upDate;
	private String title;
	private String content;
}
