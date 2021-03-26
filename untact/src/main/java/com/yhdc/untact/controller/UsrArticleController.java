package com.yhdc.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;

@Controller
public class UsrArticleController {
	private List<Article> articles;
	
	public UsrArticleController() {
		articles = new ArrayList<>();
		
		articles.add(new Article(1, "2021-04-01 12:12:12", "Title1", "Content1"));
		articles.add(new Article(2, "2021-04-01 12:12:12", "Title2", "Content2"));		
	}
	
	
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
//		Article article1 = new Article(1, "2021-04-01 12:12:12", "Title1", "Content1");
//		Article article2 = new Article(2, "2021-04-01 12:12:12", "Title2", "Content2");
//
//		Article article = null;
//		if(id == 1) {
//			article = article1;
//		}
//		else {
//			article = article2;
//		}
//		return article;
		
		return articles.get(id-1);
	}
	
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
}
