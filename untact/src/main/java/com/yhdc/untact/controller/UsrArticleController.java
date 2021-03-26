package com.yhdc.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhdc.untact.dto.Article;

@Controller
public class UsrArticleController {
	private  int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		//
		articlesLastId = 0;
		articles = new ArrayList<>();
		
		//initial data
		articles.add(new Article(++articlesLastId, "2021-04-01 12:12:12", "Title1", "Content1"));
		articles.add(new Article(++articlesLastId, "2021-04-01 12:12:12", "Title2", "Content2"));		
	}
	
	
	//Show the requested article
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		
		return articles.get(id-1);
	}
	
	
	//Show all the articles
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
	
	
	//Add an article
	@RequestMapping("/usr/article/add")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		articles.add(new Article(++articlesLastId, regDate, title, body));
		
		//CodeCheck : 'S' == Success, 'F' == Fail
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "Successfully added.");
		rs.put("id", articlesLastId);
		
		return rs;
	}
	
	
	//Delete an article
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		
		//Don't do this because it changes the length of the array and id of other articles  
		//articles.remove(id-1);
				
		boolean deleteArticleResult = deleteArticle(id);		
		
		//CodeCheck : 'S' == Success, 'F' == Fail
		Map<String, Object> rs = new HashMap<>();
		
		if(deleteArticleResult) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "Successfully added.");
		}
		else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "Failed to delete.");
		}

		rs.put("id", id);
		
		return rs;
	}
	

	//Delete an article without tempering the array
	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	
	//Update an article
	
	
	
}
